package demo02.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * 管理 bean 的生命周期的接口
 * BeanFactoryAware
 * BeanNameAware
 * InitializingBean
 * DisposableBean
 */
public class Person implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

    private int age;
    private String name;
    private String address;

    private BeanFactory beanFactory;
    private String beanName;

    public Person() {
        System.out.println("调用了Person的无参构造器");
    }

    public void setAge(int age) {
        System.out.println("调用了setAge方法设置了属性，"+age);
        this.age = age;
    }

    public void setName(String name) {
        System.out.println("调用了setName方法设置了属性，"+name);
        this.name = name;
    }

    public void setAddress(String address) {
        System.out.println("调用了setAddress方法设置了属性，"+address);
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void introduce() {
        System.out.println("调用了introduce方法--> name:" + this.name + ",age:" + age + ",address:" + address);
    }

    // BeanFactoryAware 接口方法，待调用setBeanName之后调用该方法，默认实现类是DefaultListableBeanFactory
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("调用了BeanFactoryAware.setBeanFactory, value is " + beanFactory);
        this.beanFactory = beanFactory;
    }

    // BeanNameAware 接口方法，待对象实例化并设置属性之后调用该方法
    public void setBeanName(String beanName) {
        System.out.println("调用了BeanNameAware.setBeanName, value is " + beanName);
        this.beanName = beanName;
    }

    // InitializingBean 接口方法，调用了BeanPostProcessor.postProcessBeforeInitialization方法之后调用该方法
    public void afterPropertiesSet() throws Exception {
        System.out.println("调用InitializingBean.afterPropertiesSet");
    }

    // DisposableBean 接口方法，关闭BeanFactory时执行
    public void destroy() throws Exception {
        System.out.println("调用DisposableBean.destroy");
    }

    //配置中的init-method方法，调用了InitializingBean.afterPropertiesSet之后调用
    public void myInit() {
        System.out.println("调用bean配置的init-method");
        this.address = "中国";
    }

    //配置中init-destroy方法，调用了DisposableBean.destroy之后调用
    public void myDestroy() {
        System.out.println("调用bean配置的destroy-method");
    }

}
