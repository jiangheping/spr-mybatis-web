package demo02.BeanFactoryLifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * ApplicationContext bean生命周期演示
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    //先调用本方法，将所有bean生成BeanDefinition对象并设置相关属性。
    //本方法运行完之后，会调用bean构造器，并调用set相关属性方法
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("person");
        beanDefinition.getPropertyValues().addPropertyValue("address", "王二麻子");
        System.out.println("调用了BeanFactoryPostProcessor.postProcessBeanFactory");
    }

}
