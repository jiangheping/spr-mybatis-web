package demo01;

import demo.dao.PersonMapper;
import demo.dao.PersonMapper2;
import demo.entity.Person;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;

/**
 * Created by admin on 2017/9/3.
 * 构建SqlSessionFactory的两种方式
 * 1、xml配置构建
 * 2、代码方式构建
 */
public class MybatisExample {

    @Test
    public void createSessionFactoryByXmlConfig() {
        SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactoryByXml();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        Person person = personMapper.selectByPrimaryKey(3);//org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): demo.dao.PersonMapper.selectByPrimaryKey
        System.out.println("name--->" + person.getName());
    }


    @Test
    public void createSessionFactoryByCode() {
        SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactoryByCode();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PersonMapper2 personMapper = sqlSession.getMapper(PersonMapper2.class);
        Person person = personMapper.selectByPrimaryKey(3);//org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): demo.dao.PersonMapper.selectByPrimaryKey
        System.out.println("name--->" + person.getName());
    }

}
