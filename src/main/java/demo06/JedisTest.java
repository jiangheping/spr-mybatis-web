package demo06;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisTest {
    private static final Jedis jedis = new Jedis("127.0.0.1", 6379);

    @Test
    public void test_string() {
        jedis.set("name", "zhangsan");
    }

    @Test
    public void test_hash() {
        //存放的类似map类型的
        jedis.hset("url", "google", "www.google.com");
        jedis.hset("url", "taobao", "www.taobao.com");
        jedis.hset("url", "baidu", "www.baidu.com");

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("name", "zhangsan");
        userInfo.put("age", "35");
        userInfo.put("sex", "man");
        jedis.hmset("userInfo", userInfo);
        String name = jedis.hget("userInfo", "name");
        //取多个返回值
        List<String> urlList = jedis.hmget("url", "google", "taobao", "baidu");
        //取hash所有key值
        Map<String, String> userInfoMap = jedis.hgetAll("userInfo");
    }

    @Test
    public void test_list() {
        jedis.lpush("charList", "abc");//lpush，在list首部添加元素
        jedis.rpush("charList", "def");//rpush，在listw尾部添加元素
        //截取list
        List<String> charList = jedis.lrange("charList", 0, 1);
        jedis.lpop("charList");//在list首部删除元素
        jedis.rpop("charList");//在list尾部删除元素
    }

    @Test
    public void test_set() {
        jedis.sadd("setMem", "s1");
        jedis.sadd("setMem", "s2");
        Set<String> sets = jedis.smembers("setMem");
    }

    @Test
    public void test_sort_set() {
        jedis.zadd("sortSetMem", 1, "s1");
        jedis.zadd("sortSetMem", 2, "s1");
        Set<String> sets = jedis.zrange("sortSetMem", 0, 1);
        Set<String> revesortSet = jedis.zrevrange("sortSetMem", 0, 1);//反向取
    }
}
