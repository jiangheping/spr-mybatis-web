package demo06.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.util.StringUtils;
import redis.clients.jedis.BinaryJedisCommands;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisCache<ResourceType extends BinaryJedisCommands> implements Cache {

    private String name;
    private String tag;
    private int duration;
    private TimeUnit timeUnit;
    private RedisStorage<ResourceType> redisStorage;

    public RedisCache() {
    }

    public RedisCache(RedisStorage<ResourceType> redisStorage, String name, int duration, TimeUnit timeUnit) {
        this.redisStorage = redisStorage;
        this.name = name;
        this.duration = duration;
        this.timeUnit = timeUnit;
    }

    public RedisCache(RedisStorage<ResourceType> redisStorage, String name, String tag, int duration, TimeUnit timeUnit) {
        this.redisStorage = redisStorage;
        this.name = name;
        this.duration = duration;
        this.timeUnit = timeUnit;
        this.tag = tag;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return redisStorage.getPool();
    }

    @Override
    public ValueWrapper get(Object key) {
        Object actObject = null;
        if (StringUtils.isEmpty(tag)) {
            actObject = redisStorage.get(key);
        } else {
            actObject = redisStorage.hget(tag, key);
        }
        return actObject == null ? null : new SimpleValueWrapper(actObject);
    }


    @Override
    public <T> T get(Object key, Class<T> aClass) {
        ValueWrapper valueWrapper = this.get(key);
        if (valueWrapper == null) {
            return null;
        }
        return aClass.cast(valueWrapper.get());
    }

    @Override
    public void put(Object key, Object value) {
        if (StringUtils.isEmpty(tag)) {
            redisStorage.put(key, value, (int) timeUnit.toSeconds(duration));
            return;
        }
        redisStorage.putCacheToTag(tag, key, value, duration);
    }

    @Override
    public void evict(Object key) {
        redisStorage.evict(key);//如果该方法不行，试试evictTag,key为tag
    }

    @Override
    public void clear() {
        redisStorage.destroy();
    }
}
