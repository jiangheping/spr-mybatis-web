package demo01;

import demo.dao.PersonMapper;
import demo.dao.PersonMapper2;
import demo.entity.Person;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.IOException;

/**
 * 构建SqlSessionFactory的两种方式
 * 1、xml配置构建
 * 2、代码方式构建
 */
public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory = null;

    //xml配置构建
    public static SqlSessionFactory getSqlSessionFactoryByXml() {
        if (sqlSessionFactory == null) {
            String resource = "application/mybatis_config.xml";
            try {
                sqlSessionFactory = new SqlSessionFactoryBuilder()
                        .build(Resources.getResourceAsStream(resource));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sqlSessionFactory;
    }


    //java代码构建
    public static SqlSessionFactory getSqlSessionFactoryByCode() {
        //构建数据库连接池， POOLED：这是JDBC连接对象的数据源连接池的实现，用来避免创建新的连接实例时必要的初始连接和认证时间
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername("p2puser");
        dataSource.setPassword("p2poracle");

        //事务方式
        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        //运行环境
        Environment environment = new Environment("development", transactionFactory, dataSource);

        //构建Configuration对象
        Configuration configuration = new Configuration(environment);

        //注册一个mybatis上下文别名
        configuration.getTypeAliasRegistry().registerAlias("person", Person.class);

        //加入一个映射器
        configuration.addMapper(PersonMapper2.class);//sql通过注解方式，而不是xml方式，参考：http://www.mybatis.org/mybatis-3/zh/getting-started.html

        //使用sqlsessionFactoryBuild构建sqlsessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        return sqlSessionFactory;
    }
}
