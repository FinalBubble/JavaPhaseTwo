package day20230527;

import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * 以Person类为例,测试反射
 */
public class ReflectDemo03 {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("请输入类名：");
        String className = new Scanner(System.in).nextLine();
        Class cls = Class.forName(className);
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }
}
