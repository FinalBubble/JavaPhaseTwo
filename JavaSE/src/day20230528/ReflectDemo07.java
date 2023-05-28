package day20230528;

import day20230527.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * 使用反射机制调用方法
 */
public class ReflectDemo06 {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
        Person person = new Person();
        person.sayHello();
        //1、实例化
        Class cls = Class.forName("day20230527.Person");
        Object o = cls.newInstance();
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
        System.out.println("请输入方法名：");
        String methodName = new Scanner(System.in).nextLine();
        //2、根据方法名获取要调用的方法
        Method method = cls.getMethod(methodName);
        //3、调用方法
        method.invoke(o);
    }
}
