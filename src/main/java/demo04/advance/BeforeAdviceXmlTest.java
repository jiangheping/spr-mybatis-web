package demo04.advance;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeforeAdviceXmlTest {
    @Test
    public void before_advice_xml_test() {
        String path = "beans/beans.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(path);
        Waiter waiter = context.getBean("waiter", Waiter.class);
        waiter.greetTo("JayChou");
        waiter.serveTo("JayChou");
    }
}
