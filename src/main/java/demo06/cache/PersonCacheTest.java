package demo06.cache;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonCacheTest {

    private PersonService2 personService2;

    @Before
    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans/application_redis_cache.xml");
        personService2 = context.getBean("personService2", PersonService2.class);
    }

    @Test
    public void testGetPersonByName() {
        System.out.println("第一次查询………………");
        personService2.getPersonByName("张三");
        System.out.println("第二次查询………………");
        personService2.getPersonByName("李四");
        System.out.println("第三次查询………………");
        personService2.getPersonByName("张三");
    }
}
