package demo06;

import demo.entity.Person;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.HashMap;
import java.util.Map;

public class MyCache implements Cache {

    private String name;
    private Map<String, Person> store = new HashMap<String, Person>();

    public void setName(String name) {
        this.name = name;
    }

    public MyCache() {
    }

    public MyCache(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return store;
    }

    @Override
    public ValueWrapper get(Object key) {
        ValueWrapper result = null;
        Person person = store.get(key);
        if (person != null) {
            result = new SimpleValueWrapper(person);
        }
        return result;
    }

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        Person person = (Person) value;
        store.put((String) key, person);
    }

    @Override
    public void evict(Object key) {
        if (store.containsKey(key)) {
            store.remove(key);
        }
    }

    @Override
    public void clear() {
        store.clear();
    }
}
