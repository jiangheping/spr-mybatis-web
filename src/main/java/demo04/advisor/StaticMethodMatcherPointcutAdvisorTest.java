package demo04.advisor;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StaticMethodMatcherPointcutAdvisorTest {
    @Test
    public void test() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans/beans.xml");
        Waiter waiter = ctx.getBean("waiter2", Waiter.class);
        Seller seller = ctx.getBean("seller", Seller.class);
        waiter.greetTo("JayChou");
        waiter.serverTo("JayChou");
        seller.greetTo("JayChou");
    }
}
