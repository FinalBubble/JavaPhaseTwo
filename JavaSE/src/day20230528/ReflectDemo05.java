package day20230528;

import day20230527.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 使用反射机制创建含参对象
 */
public class ReflectDemo05 {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
        Person person = new Person("李四", 33);
        System.out.println(person);
        //1、加载类对象
        Class cls = Class.forName("day20230527.Person");
        //2、获取对应的构造器 public Person(String name, int age) {}
        Constructor constructor = cls.getConstructor(String.class, int.class);
        Object o = constructor.newInstance("王五", 44);
        System.out.println(o);
    }
}
