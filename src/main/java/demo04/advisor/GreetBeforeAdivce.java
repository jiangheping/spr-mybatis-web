package demo04.advisor;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 前置增强
 */
public class GreetBeforeAdivce implements MethodBeforeAdvice {
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        // 输出切点
        String clientName = (String) objects[0];
        System.out.println("How are you " + clientName + " ?（切点方法为：" + method + "）");
    }
}
