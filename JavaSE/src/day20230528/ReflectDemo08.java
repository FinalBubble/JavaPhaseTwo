package day20230528;

import day20230527.Person;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * 使用反射机制暴力反射
 */
public class ReflectDemo08 {
    public static void main(String[] args) throws InvocationTargetException,
            InstantiationException,
            IllegalAccessException,
            ClassNotFoundException,
            NoSuchMethodException {
        //1、实例化
        Class cls = Class.forName("day20230527.Person");
        Object o = cls.newInstance();
        //Method[] declaredMethods = cls.getDeclaredMethods();
        //for (Method declaredMethod : declaredMethods) {
        //    System.out.println(declaredMethod);
        //}
        //System.out.println("请输入方法名：");
        //String methodName = new Scanner(System.in).nextLine();
        //指定方法名和参数类型，获取指定的方法
        /**
         * Exception in thread "main"
         * java.lang.NoSuchMethodException: day20230527.Person.secret()
         * Method method = cls.getMethod("secret")
         */

        Method method = cls.getDeclaredMethod("secret");
        //反射机制具备强行打开访问权限的功能
        method.setAccessible(true);
        method.invoke(o);
        //一旦反射机制打开了权限，用完之后，及时关闭
        method.setAccessible(false);

    }
}
