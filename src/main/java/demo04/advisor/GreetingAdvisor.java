package demo04.advisor;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * 静态普通方法名匹配切面
 */
public class GreetingAdvisor extends StaticMethodMatcherPointcutAdvisor {
    /**
     * 重写matches方法,切点方法匹配规则：方法名为greetTo
     */
    public boolean matches(Method method, Class<?> aClass) {
        return "greetTo".equals(method.getName());
    }

    /**
     * 默认情况下，匹配所有的类，重写getClassFilter，定义匹配规则切点类型匹配规则，为Waiter的类或者之类
     */
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            public boolean matches(Class<?> clazz) {
                return Waiter.class.isAssignableFrom(clazz);
            }
        };
    }
}
