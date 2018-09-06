package demo06;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonCacheTest {

    private PersonService personService;

    @Before
    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans/application_cache.xml");
        personService = context.getBean("personService", PersonService.class);
    }

    @Test
    public void testGetPersonByName() {
        System.out.println("第一次查询………………");
        personService.getPersonByName("张三");
        System.out.println("第二次查询………………");
        personService.getPersonByName("李四");
        System.out.println("第三次查询………………");
        personService.getPersonByName("张三");
    }
}
