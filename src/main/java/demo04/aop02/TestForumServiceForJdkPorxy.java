package demo04.aop02;

import demo04.aop01.ForumService;
import demo04.aop01.ForumServiceImpl;

import java.lang.reflect.Proxy;

/**
 * Proxy利用InvocationHandler动态创建一个符合某一接口的实例，生成目标类的代理对象
 */
public class TestForumServiceForJdkPorxy {
    public static void main(String[] args) {
        ForumService target = new ForumServiceImpl();
        PerformaceHandler performaceHandler = new PerformaceHandler(target);
        ForumService proxy = (ForumService) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),performaceHandler);
        proxy.removeForum(1);
    }
}
