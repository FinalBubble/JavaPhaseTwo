package day20230528;

import day20230527.Person;

import java.util.Scanner;

/**
 * 使用反射机制创建对象
 */
public class ReflectDemo04 {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        //传统实例化对象的方式
        Person person = new Person();
        System.out.println(person);
        //反射机制实例化对象
        System.out.println("请输入要实例化的全路径：");
        String className = new Scanner(System.in).nextLine();
        //1、获取要操作的类的实例对象
        Class cls = Class.forName(className);
        //2、（1）获取类中的公开的无参构造 （2）实例化该对象
        //InstantiationException 实例化异常
        //IllegalAccessException 非法访问异常
        Object o = cls.newInstance();
        System.out.println(o);


    }
}
