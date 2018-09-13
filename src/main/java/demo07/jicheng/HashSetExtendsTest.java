package demo07.jicheng;

import org.junit.Test;

import java.util.Arrays;

public class HashSetExtendsTest {
    @Test
    public void test() {
        HashSetExtends<String> str = new HashSetExtends<>();
        str.addAll(Arrays.asList("zhou", "li", "wang"));
        System.out.println(str.getCount());//6
    }
}
