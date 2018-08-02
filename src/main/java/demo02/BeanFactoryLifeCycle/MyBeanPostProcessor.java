package demo02.BeanFactoryLifeCycle;

import demo02.bean.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 实例化完成之后调用该接口
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    //实例化完成，setBeanName/setBeanFactory完成之后调用该方法
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        if ("person".equals(s)) {
            Person person = (Person) o;
            if (person.getName() == null) {
                System.out.println("调用BeanPostProcessor.postProcessBeforeInitialization,name为空，设置默认名为无名氏");
                person.setName("无名氏");
            }
        }
        return o;
    }

    //全部是实例化完成以后调用该方法
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        if ("person".equals(s)) {
            Person person = (Person) o;
            if (person.getAge()>20) {
                System.out.println("调用BeanPostProcessor.postProcessAfterInitialization,age大于20，调整为20");
                person.setAge(20);
            }
        }
        return o;
    }

}
