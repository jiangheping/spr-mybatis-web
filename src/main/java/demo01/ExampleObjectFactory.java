package demo01;


import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Properties;

public class ExampleObjectFactory extends DefaultObjectFactory {

    Logger log = Logger.getLogger(ExampleObjectFactory.class);

    @Override
    public void setProperties(Properties properties) {
        log.info("=====>定制属性：" + properties);
        super.setProperties(properties);
    }

    @Override
    public <T> T create(Class<T> type) {
        log.info("=====>使用定制对象工厂的create方法构建单个对象");
        return super.create(type);
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> list, List<Object> list1) {
        log.info("=====>使用定制对象工厂的create方法构建列表对象");
        return super.create(type, list, list1);
    }
}