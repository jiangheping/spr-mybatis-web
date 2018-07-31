package demo02;

import demo02.bean.Car;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

public class BeanFactoryTest {
    @Test
    public void getBean() throws IOException {
        //通过资源加载器获取加载文件资源
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("beans/beans.xml");
        System.out.println(resource.getURL());

        //获取beanFactory二种方式

        //方式一 XmlBeanFactory废弃，不建议使用
        BeanFactory factory1 = new XmlBeanFactory(resource);

        System.out.println("--------------------1----------------------");
        //方式二 DefaultListableBeanFactory
        DefaultListableBeanFactory factory2 = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory2);
        reader.loadBeanDefinitions(resource);
        System.out.println("init beanFactory");
        Car car = (Car)factory2.getBean("car");//第一次调用factory.getBean方法才会调用默认构造器生成实例
        System.out.println("car bean is ready for user");
        car.introduce();

        System.out.println("--------------------2----------------------");
        //spring在DefaultSingletonBeanRegistry类中提供了缓存单例bean，用HashMap实现的，以beanName为key进行保存，所以第二次get对象的时候不会调用构造器
        Car car2 = (Car)factory2.getBean("car");
        car2.introduce();
        System.out.println(car==car2);//true,bean定义的默认是单例

        //获取ApplicationContext用ClassPathXmlApplicationContext
    }

    //获取 ApplicationContext 的三种方式
    @Test
    public void getBeanByApplicationContext(){
        //配置文件放在类路径上，则用ClassPathXmlApplicationContext
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans/beans.xml");
        Car car = (Car)applicationContext.getBean("car");
        car.introduce();

        //配置文件放在文件系统下，则用FileSystemXmlApplicationContext
//        ApplicationContext ac = new FileSystemXmlApplicationContext("beans/beans.xml");
//        Car car2 = (Car)ac.getBean("car");
//        car2.introduce();

        //@Configuration注解获取ApplicaitonContext方式
//        ApplicationContext ac = new AnnotationConfigApplicationContext(Car.class);
//        Car car2 = (Car)ac.getBean("car");
    }
}
