package demo05;

import demo02.bean.Person;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * 子类重写父类的方法，运行时根据子类对象会运行子类的方法
 * <p>
 * 这里没有进行分页查询了，如果数据量大，需要分页进行查询时，
 * 可以重写getTotalCount()／getBatchCount()方法进行分页处理
 */
public class BatchJobExample extends BatchJob<Person> {
    @Override
    protected List fetchList(int batchCount) {
        Person person1 = new Person();
        Person person2 = new Person();
        Person person3 = new Person();
        return asList(person1, person2, person3);
    }

    @Override
    protected void processList(List<Person> dataList) {
        for (Person person : dataList) {
            System.out.println(person);
        }
    }


}
