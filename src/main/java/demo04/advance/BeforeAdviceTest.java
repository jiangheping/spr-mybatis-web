package demo04.advance;

import org.junit.Test;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class BeforeAdviceTest {

    @Test
    public void before_cglib_proxy() {
        //创建目标对象
        NativeWaiter target = new NativeWaiter();
        //创建前置增强器
        BeforeAdvice advice = new GreetingBeforeAdvice();
        //创建代理工厂对象
        ProxyFactory pf = new ProxyFactory();
        //设置代理类
        pf.setTarget(target);
        //也可以启动优化代理方式，会使用cglib来生成代理。当使用接口时，只要调用了该方法，都会使用cglib方式来生成代理对象
        //pf.setOptimize(true);
        //设置增强类
        pf.addAdvice(advice);
        //生成代理实例
        NativeWaiter proxy = (NativeWaiter) pf.getProxy();
        proxy.greetTo("John");
        proxy.serveTo("Tom");
    }

    @Test
    public void before_jdk_proxy() {
        //创建目标对象
        Waiter target = new NativeWaiter();
        //创建前置增强器
        BeforeAdvice advice = new GreetingBeforeAdvice();
        //创建代理工厂对象
        ProxyFactory pf = new ProxyFactory();
        //设置代理接口
        pf.setInterfaces(target.getClass().getInterfaces());
        //设置代理类
        pf.setTarget(target);
        //设置增强类
        pf.addAdvice(advice);
        //生成代理实例
        Waiter proxy = (Waiter) pf.getProxy();
        proxy.greetTo("John");
        proxy.serveTo("Tom");
    }


}
