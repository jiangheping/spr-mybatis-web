package demo04.aop02;

import demo04.aop01.PerformanceMonitor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * InvocationHandler 接口，可以通过实现该接口定义横切逻辑
 * 并通过反射机制调用目标类的代码
 */
public class PerformaceHandler implements InvocationHandler {
    private Object target;
    public PerformaceHandler(Object target){
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        PerformanceMonitor.begin(target.getClass().getName()+"."+ method.getName());
        Object obj = method.invoke(target, args);
        PerformanceMonitor.end();
        return obj;
    }
}
