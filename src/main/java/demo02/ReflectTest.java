package demo02;

import demo02.bean.Car;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法和属性；这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。
 * <p>
 * 获取Class对象的三种方式：
 * 1 Object ——> getClass();
 * 2 任何数据类型（包括基本数据类型）都有一个“静态”的class属性
 * 3 通过Class类的静态方法：forName（String  className）(常用)
 */
public class ReflectTest {

    private static Car init() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //获取类装载器，获取类对象
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("demo02.bean.Car");
//        //1.加载Class对象 也可以这样获取类对象
//        Class clazz = Class.forName("demo02.bean.Car");
        //获取构造器方法并生成对象
        Constructor constructor = clazz.getDeclaredConstructor(null);
        Car car = (Car) constructor.newInstance();
        //通过反射方法设置属性
        Method setBrand = clazz.getMethod("setBrand", String.class);
        Method setColor = clazz.getMethod("setColor", String.class);
        Method setMaxSpeed = clazz.getMethod("setMaxSpeed", int.class);
        setBrand.invoke(car, "宝马");
        setColor.invoke(car, "黑色");
        setMaxSpeed.invoke(car, 230);
        return car;
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Car car = init();
        car.introduce();
    }

}
