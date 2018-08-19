package demo02.BeanFactoryLifeCycle;

import demo02.bean.Person;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * BeanFactory中的Bean的生命周期
 * 容器装载配置文件，注册 BeanPostProcessor 和 InstantiationAwareBeanPostProcessorAdapter 后置处理器
 */
public class BeanFactoryLifeCycleMain {

    @Test
    public void lifeCycleInBeanFactory() {
        //装载配置文件并启动容器
        Resource resource = new ClassPathResource("beans/beans.xml");
        BeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((DefaultListableBeanFactory) beanFactory);
        reader.loadBeanDefinitions(resource);

        //向容器中注册后处理器 MyBeanPostProcessor
        ((DefaultListableBeanFactory) beanFactory).addBeanPostProcessor(new MyBeanPostProcessor());

        //向容器中注册后处理器 MyInstantiationAwareBeanPostProcessor
        //注意：后处理器调用顺序和注册顺序无关。在处理多个后处理器的情况下，必需通过实现Ordered接口来确定顺序
        ((DefaultListableBeanFactory) beanFactory).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        //第一次从容器中获取Person，将触发容器实例化该bean，这将引发Bean生命周期方法的调用
        Person person = (Person) beanFactory.getBean("person");
        person.introduce();
        person.setName("zhangsan");

        //第二次从容器中获取，直接从缓存中获取(默认就是单例)
        Person person2 = (Person) beanFactory.getBean("person");
        System.out.println(person == person2);//true

        //关闭容器
        ((DefaultListableBeanFactory) beanFactory).destroySingletons();
    }

}
