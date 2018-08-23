package demo04.advisor;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DynamicMethodMatcherPointcutTest {
    @Test
    public void test() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans/beans.xml");
        Waiter waiter = ctx.getBean("waiter3", Waiter.class);
        Seller seller = ctx.getBean("seller", Seller.class);
        waiter.greetTo("JayChou");
        waiter.greetTo("JayChou2");
        waiter.greetTo("JayChou3");
        waiter.serverTo("JayChou");
        waiter.serverTo("JayChou2");
        waiter.serverTo("JayChou3");
        seller.greetTo("LiHong");
    }
}
