package demo04.proxy;

import demo04.advisor.Seller;
import demo04.advisor.Waiter;
import demo04.advisor.WaiterDelegate;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanNameAutoProxyCreatorTest {
    //一组特定配置名的Bean自动创建代理实例的代理创建器
    @Test
    public void test() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans/beans2.xml");
        Waiter waiter = ctx.getBean("waiterTarget", Waiter.class);
        Seller seller = ctx.getBean("seller", Seller.class);
        waiter.greetTo("JayChou");
        waiter.serverTo("JayChou");
        seller.greetTo("JayChou");
    }
}
