package demo06.cache;

import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建jedis连接
 * 单个连接 JedisPool
 * 集群连接 ShardedJedisPool
 */
public class RedisStorageFactory {
    public RedisStorage<? extends BinaryJedisCommands> newStorage(JedisPoolConfig poolConfig, List<JedisShardInfo> shardInfos) {
        List<HostAndPort> hostAndPortList = getHostAndPortByShardInfos(shardInfos);
        if (shardInfos.size() == 1) {
            return new RedisStorage<>(new JedisPool(poolConfig, shardInfos.get(0).getHost(), shardInfos.get(0).getPort(), shardInfos.get(0).getSoTimeout(), shardInfos.get(0).getPassword()), hostAndPortList);
        }
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(poolConfig, shardInfos);
        return new RedisStorage<>(shardedJedisPool, hostAndPortList);
    }

    private List<HostAndPort> getHostAndPortByShardInfos(List<JedisShardInfo> shardInfos) {
        List<HostAndPort> hostAndPorts = new ArrayList<>();
        for (JedisShardInfo shardInfo : shardInfos) {
            hostAndPorts.add(new HostAndPort(shardInfo.getHost(), shardInfo.getPort()));
        }
        return hostAndPorts;
    }


}
