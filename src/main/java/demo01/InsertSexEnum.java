package demo01;

import demo.dao.PersonMapper;
import demo.entity.Person;
import demo.enumPackage.Sex;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

/**
 * 测试自定义枚举类型typeHandler
 * step:
 * 1、java定义相关枚举，并引入实体类中
 * 2、mybatis_config中配置typeHandlers
 * 3、mapper.xml中该字段配置；然后就可以引用了
 *
 * EnumTypeHandler：使用枚举字符串名称作为参数传递
 * EnumOrdinalTypeHandler:使用整数下标作为参数传递，从0开始
 *
 */
public class InsertSexEnum {

    @Test
    public void testInsertSexEnum(){
        SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactoryByXml();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);

        Person person = new Person();
        person.setId(5);
        person.setName("林心如");
        person.setSex(Sex.FEMALE);
        person.setDescription("霍建华老婆，我前女友");
        personMapper.insert(person);

        sqlSession.commit();
    }


}
