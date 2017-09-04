package demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.dao.PersonMapper;
import demo.entity.Person;
import demo.service.IPersonService;

@Service("personService")  
public class PersonServiceImpl implements IPersonService {

	@Autowired  
    private PersonMapper personMapper;

	@Override
	public Person queryById(Integer id) {
		//ApplicationContext  ac = new ClassPathXmlApplicationContext("classpath:application/applicationContext-datasource.xml");
		//personMapper = (PersonMapper) ac.getBean("personMapper");
		return this.personMapper.selectByPrimaryKey(id);
	}
	
}
