package demo06.cache;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

public class SpringDataCacheManager extends RedisCacheManager implements ApplicationContextAware, InitializingBean, CacheManager {

    private ApplicationContext applicationContext;
    private final Map<String, CacheDefinition> caches = new ConcurrentReferenceHashMap<>();
    private int defaultDuration = 60;//默认缓存60秒
    private String servers = "127.0.0.1^7100^password,127.0.0.1^6379^password";

    //InitializingBean的实现方法
    @Override
    public void afterPropertiesSet() throws Exception {
        setServers(servers);
        parseFromContext(applicationContext);
    }

    private void parseFromContext(final ApplicationContext context) {
        String[] beanNames = context.getBeanNamesForType(Object.class);
        for (final String beanName : beanNames) {
            final Class<?> classType = context.getType(beanName);
            //查询该类是否有service和repository注解
            Service service = findAnnotation(classType, Service.class);
            Repository repository = findAnnotation(classType, Repository.class);
            if (service == null && repository == null) {
                continue;
            }
            ReflectionUtils.doWithMethods(classType, new ReflectionUtils.MethodCallback() {
                public void doWith(Method method) {
                    ReflectionUtils.makeAccessible(method);
                    CacheMapping cacheMapping = findCacheMapping(method, classType);//先找类级别，没有再找当前方法级别
                    Cacheable cacheable = findAnnotation(method, Cacheable.class);
                    String[] cacheNames = getCacheNamesWithCacheable(cacheable);
                    for (String cacheName : cacheNames) {
                        CacheDefinition cacheDefinition = new CacheDefinition()
                                .setName(cacheName)
                                .setSeconds(cacheMapping.duration())
                                .setTag(cacheMapping.tag());
                        caches.put(cacheName, cacheDefinition);
                    }
                }
            }, new ReflectionUtils.MethodFilter() {
                public boolean matches(Method method) {
                    return !method.isSynthetic() && findAnnotation(method, Cacheable.class) != null;
                }
            });
            if (context.getParent() != null) {
                parseFromContext(context.getParent());
            }
        }
    }

    private CacheMapping findCacheMapping(Method method, Class<?> handlerType) {
        CacheMapping cacheMapping = findAnnotation(handlerType, CacheMapping.class);
        if (cacheMapping == null) {//如果类注解没有，再从方法注解查询
            cacheMapping = findAnnotation(method, CacheMapping.class);
        }
        if (cacheMapping == null) {
            throw new IllegalStateException("No cache mapping (@CacheMapping) could be detected on '" +
                    method.toString() + "'. Make sure to set the value parameter on the annotation or " +
                    "declare a @CacheMapping at the class-level with the default cache cacheMapping to use.");
        }
        return cacheMapping;
    }

    private String[] getCacheNamesWithCacheable(Cacheable cacheable) {
        return cacheable.value();
    }

    //CacheManager的实现方法
    @Override
    public Cache getCache(String name) {
        CacheDefinition cacheDefinition = caches.get(name);
        if (null != cacheDefinition) {
            return new RedisCache<>(newStorage(), name, cacheDefinition.getTag(), cacheDefinition.getSeconds(), TimeUnit.SECONDS);
        }
        return new RedisCache<>(newStorage(), name, defaultDuration, TimeUnit.SECONDS);
    }

    @Override
    public Collection<String> getCacheNames() {
        return caches.keySet();
    }

    //ApplicationContextAware
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
