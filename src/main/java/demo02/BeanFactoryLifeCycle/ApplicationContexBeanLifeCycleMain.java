package demo02.BeanFactoryLifeCycle;

import demo02.bean.Car;
import demo02.bean.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ApplicationContext bean生命演示
 */
public class ApplicationContexBeanLifeCycleMain {
    public static void main(String[] args) {
        //ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans/beans.xml");
        //ApplicationContext 接口中没有实现close方法，所以可以用该类（该类是实现了ConfigurableApplicationContext接口和DisposableBean接口）
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans/beans.xml");
        Person person = (Person) applicationContext.getBean("person");
        person.introduce();
        applicationContext.close();
    }
}
