package demo04.advisor;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DefaultPointcutAdvisorTest {
    @Test
    public void test() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans/beans.xml");
        Waiter waiter = ctx.getBean("waiter4", Waiter.class);
        waiter.greetTo("JayChou");
        waiter.serverTo("JayChou");
        WaiterDelegate wd = new WaiterDelegate();
        wd.setWaiter(waiter);
        wd.waiterService("LiHong");
    }
}
