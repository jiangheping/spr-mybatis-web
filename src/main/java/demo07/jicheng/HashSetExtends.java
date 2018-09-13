package demo07.jicheng;

import java.util.Collection;
import java.util.HashSet;

public class HashSetExtends<E> extends HashSet<E> {
    public int count = 0;

    public HashSetExtends() {
    }

    public HashSetExtends(int initCap, float loadFactory) {
        super(initCap, loadFactory);
    }

    @Override
    public boolean add(E e) {
        System.out.println("这里调用了");
        count++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        count += c.size();
        return super.addAll(c);
    }

    public int getCount(){
        return count;
    }


}
