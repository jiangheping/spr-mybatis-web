package demo02.BeanFactoryLifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

/**
 * 实例化前／后，及框架设置Bean属性时调用该接口
 */
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    //在Bean对象实例化前调用
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        //仅对容器中的person bean处理
        if ("person".equals(beanName)) {
            System.out.println("实例化前调用：InstantiationAwareBeanPostProcessorAdapter.postProcessBeforeInstantiation");
        }
        return null;
    }

    //在Bean对象实例化后调用（如调用构造器之后调用）
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        //仅对容器中的person bean处理
        if ("person".equals(beanName)) {
            System.out.println("实例化后调用：InstantiationAwareBeanPostProcessorAdapter.postProcessAfterInstantiation");
        }
        return true;
    }

    /**
     * 在设置某个属性前调用，然后再调用设置属性的方法
     * 注意：这里的设置属性是指通过配置设置属性，直接调用对象的setXX方法不会调用该方法，如bean配置中配置了属性address/age属性，将会调用该方法
     * @param pvs 如 PropertyValues: length=2; bean property 'address'; bean property 'age'
     */
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        //仅对容器中的person bean处理
        if ("person".equals(beanName)) {
            System.out.println("在Bean设置属性时调用：InstantiationAwareBeanPostProcessorAdapter.postProcessPropertyValues，设置值为" + pvs);
        }
        return pvs;
    }

}
