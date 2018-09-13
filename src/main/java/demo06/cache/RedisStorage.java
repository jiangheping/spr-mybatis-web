package demo06.cache;


import org.apache.log4j.Logger;
import org.springframework.core.serializer.DefaultDeserializer;
import org.springframework.core.serializer.DefaultSerializer;
import org.springframework.core.serializer.Deserializer;
import org.springframework.core.serializer.Serializer;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.util.StringUtils;
import redis.clients.jedis.BinaryJedisCommands;
import redis.clients.jedis.HostAndPort;
import redis.clients.util.Pool;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RedisStorage<ResourceType extends BinaryJedisCommands> implements Storage {

    //序列化默认为jdk序列化，也可以选择为hessian2／kryo
    private Serializer<Object> serializer = new DefaultSerializer();
    private Deserializer<Object> deserializer = new DefaultDeserializer();
    private final static String CACHE_TAG_PREFIX = "aaaaaaTAG_DATA";
    private Logger log = Logger.getLogger(RedisStorage.class);


    private final Pool<ResourceType> pool;
    private final List<HostAndPort> hostAndPorts;

    public RedisStorage(Pool<ResourceType> pool, List<HostAndPort> hostAndPorts) {
        this.pool = checkNotNull(pool);
        this.hostAndPorts = checkNotNull(hostAndPorts);
    }

    //下面方法为Storage接口的方法实现
    @Override
    public Object get(Object key) {
        ResourceType resource = getResource();
        byte[] bytes = resource.get(key.toString().getBytes());
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        DeserializingConverter dc = new DeserializingConverter(deserializer);
        return dc.convert(bytes);//反序列化成对象
    }

    @Override
    public void put(Object key, Object value, int seconds) {
        ResourceType resource = getResource();
        SerializingConverter sc = new SerializingConverter(serializer);
        byte[] bytes = sc.convert(value);
        resource.setex(key.toString().getBytes(), seconds, bytes);
    }

    @Override
    public void evict(Object key) {
        ResourceType resource = getResource();
        if (resource.exists(key.toString().getBytes())) {
            resource.del(key.toString().getBytes());
        }
    }

    @Override
    public void replace(Object key, Object value, int seconds) {
        put(key, value, seconds);
    }

    @Override
    public void destroy() {
        if (!pool.isClosed()) {
            pool.close();
        }
    }

    //返回增加的条数(如果key存在update，update返回0)
    public Long hset(final Object key, final Object field, final Object value, int seconds) {
        ResourceType resource = getResource();
        SerializingConverter sc = new SerializingConverter(serializer);
        byte[] bytes = sc.convert(value);
        long result = resource.hset(key.toString().getBytes(), field.toString().getBytes(), bytes);
        resource.expire(key.toString().getBytes(), seconds);
        return result;
    }

    public Set<Object> hkeys(String tag) {
        ResourceType resource = getResource();
        Set<byte[]> bytes = resource.hkeys(tag.getBytes());
        Set<Object> stringSet = new HashSet<>();
        for (byte[] bytes1 : bytes) {
            stringSet.add(new String(bytes1));
        }
        return stringSet;
    }

    public Object hget(Object tag, Object key) {
        ResourceType resource = getResource();
        byte[] bytes = resource.hget(tag.toString().getBytes(), key.toString().getBytes());
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        DeserializingConverter dc = new DeserializingConverter(deserializer);
        return dc.convert(bytes);//反序列化成对象
    }

    //标签类缓存，利用hash数据类型（类似map），可以保存某一类的缓存
    //hash类型： key(field,value)
    public void putCacheToTag(Object tag, Object key, Object value, int duration) {
        if (StringUtils.isEmpty(tag)) {
            return;
        }
        Long resultCode = this.hset(tag, key, value, duration);
        if (null == resultCode) {
            throw new IllegalArgumentException("Fail to put cache to tag");
        }
    }

    //根据标签删除相关缓存
    //如果可以通过key删除，该方法可以不要
    public void evictTag(String tag) {
        if (StringUtils.isEmpty(tag)) {
            return;
        }
        this.evict(tag);
        Set<Object> keys = this.hkeys(tag);
        log.info("Removed cache tag " + tag);
        for (Object k : keys) {
            log.info("Removed cache,tag is " + tag + ",key is " + k.toString());
        }
    }


    //检测是否可用
    @Override
    public boolean available() {
        ResourceType resource = getResource();
        byte[] bytes = resource.echo("test".getBytes());
        if (bytes == null || bytes.length == 0) {
            return false;
        }
        return Arrays.equals(bytes, "test".getBytes());
    }

    private static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public Pool<ResourceType> getPool() {
        return pool;
    }

    private ResourceType getResource() {
        return pool.getResource();
    }
}
