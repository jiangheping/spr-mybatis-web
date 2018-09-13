package demo07.fuhe;

import demo.entity.Person;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class InstrumentedSetTest {
    @Test
    public void test() {
        Set<Person> personSet = new HashSet<>();
        personSet.add(new Person());
        personSet.add(new Person());
        personSet.add(new Person());
        InstrumentedSet<Person> str = new InstrumentedSet<>(new HashSet<>());
        str.addAll(personSet);
        System.out.println(str.getAddCount());
    }
}
