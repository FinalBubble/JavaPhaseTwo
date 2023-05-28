package day20230528;

import day20230527.Person;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * 使用反射机制调用方法
 */
public class ReflectDemo07 {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
        Person person = new Person();
        person.doSome("学Java");
        //1、实例化
        Class cls = Class.forName("day20230527.Person");
        Object o = cls.newInstance();
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
        System.out.println("请输入方法名：");
        String methodName = new Scanner(System.in).nextLine();
        //指定方法名和参数类型，获取指定的方法
        Method method1 = cls.getMethod("doSome", String.class);
        //3、如果调用的是含参方法，必须要在对象后面，添加参数值
        method1.invoke(o,"学Java");
        Method method2 = cls.getMethod("doSome", String.class, int.class);
        method2.invoke(o,"玩游戏",12);
    }
}
