package demo06;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

public class PersonKeyGenerator implements KeyGenerator{

    @Override
    public Object generate(Object o, Method method, Object... objects) {
        return null;
    }
}
