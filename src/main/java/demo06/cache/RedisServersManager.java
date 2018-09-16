package demo06.cache;

import org.apache.log4j.Logger;
import redis.clients.jedis.BinaryJedisCommands;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

import java.util.ArrayList;
import java.util.List;

public class RedisServersManager {

    private Logger log = Logger.getLogger(RedisServersManager.class);
    //jedis连接分片信息
    private List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();

    protected volatile RedisStorage<? extends BinaryJedisCommands> redisStorage;
    protected final static Object REDIS_STORAGE_LOCK = new Object();
    protected RedisStorageFactory factory = new RedisStorageFactory();
    protected JedisPoolConfig config = new JedisPoolConfig();

    /**
     * 根据servers生成jedis分片连接池
     */
    public void setServers(String servers) {
        String[] serversArrays = servers.trim().split(",");
        for (String server : serversArrays) {
            JedisShardInfo jedisShardInfo = shardInfo(server);
            this.shards.add(jedisShardInfo);
            log.info("Add a node to redis shard info,node info:" + jedisShardInfo);
        }
    }

    private JedisShardInfo shardInfo(String server) {
        String[] texts = server.split("\\^");
        JedisShardInfo shard = new JedisShardInfo(texts[0], Integer.parseInt(texts[1]), 1000);
        if (texts.length == 3) {
            shard.setPassword(texts[2]);
        }
        return shard;
    }

    public RedisStorage<? extends BinaryJedisCommands> newStorage() {
        if (null == redisStorage) {
            synchronized (REDIS_STORAGE_LOCK) {
                if (null == redisStorage) {
                    redisStorage = factory.newStorage(config, shards);
                    log.info("Successfully created a redis storage.");
                }
            }
        }
        return redisStorage;
    }

}
