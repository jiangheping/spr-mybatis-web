package demo.dao;

import demo.entity.Person;
import demo.entity.Person2;
import org.apache.ibatis.annotations.Select;

/**
 * Created by admin on 2017/9/3.
 */
public interface PersonMapper2 {

    @Select("SELECT * FROM PERSON WHERE id = #{id}")
    Person2 selectByPrimaryKey(Integer id);

}
