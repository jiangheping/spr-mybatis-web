package demo04.advisor;

import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.List;

import static java.util.Arrays.asList;


/**
 * 会先执行静态切点检查，再执行动态切点检查
 */
public class GreetingDynamicPointcut extends DynamicMethodMatcherPointcut {
    private static List<String> specialClientList = asList("JayChou", "LiHong");

    /**
     * 对方法进行静态切点检查
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        System.out.println("调用了" + targetClass.getName() + "." + method.getName() + "做静态检查.");
        return "greetTo".equals(method.getName());
    }

    /**
     * 对方法进行动态切点检查
     * 如果返回false，那么该切点即不匹配
     * 思考：既然先匹配静态方法，那么为啥还需要动态匹配？
     * 原因：可以在运行期根据方法入参的值来判断增强是否需要织入目标类的连接点上
     */
    public boolean matches(Method method, Class<?> aClass, Object[] objects) {
        String clientName = (String) objects[0];
        System.out.println("调用了" + aClass.getName() + "." + method.getName() + "." + clientName + "做动态检查.");
        return specialClientList.contains(clientName);
    }

}
