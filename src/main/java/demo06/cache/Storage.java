package demo06.cache;

/**
 * 缓存方法
 */
public interface Storage {

    Object get(Object key);

    void put(Object key, Object value, int seconds);//seconds，过期时间，单位秒

    void evict(Object key);

    void replace(Object key, Object value, int seconds);

    void destroy();

    boolean available();
}
