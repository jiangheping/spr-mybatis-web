package demo06.cache;

import org.springframework.cache.annotation.Cacheable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义一个缓存，支持类级别和方法级别，如果同时存在，类级别覆盖方法级别配置
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface CacheMapping {

    /**
     * 缓存时间(秒)
     *
     * @return
     */
    public int duration() default 60;

    /**
     * 缓存标签，用于清理缓存
     *
     * @return
     */
    public String tag() default "";
}
