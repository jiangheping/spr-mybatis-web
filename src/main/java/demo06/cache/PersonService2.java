package demo06.cache;

import demo.entity.Person;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PersonService2 {

    @Cacheable(value = "getPersonByName", key = "'PersonService.getPersonByName'+#name")
    @CacheMapping(duration = 20, tag = "PERSON")
    public Person getPersonByName(String name) {
        // 方法内部实现不考虑缓存逻辑，直接实现业务
        System.out.println("调用了Service方法");
        return getFromDB(name);
    }

    private Person getFromDB(String name) {
        System.out.println("从数据库查询了");
        return new Person();
    }
}
