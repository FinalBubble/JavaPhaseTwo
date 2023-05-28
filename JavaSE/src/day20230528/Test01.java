package day20230528;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 自动调用Person本类中所有名字含有"s"的无参公开方法
 * 提示:
 * 1)获取Person的Class实例,并实例化Person实例
 * 2)获取Person本类中的所有方法
 * 3)判断每个方法是否符合要求
 * 方法名.contains("s") 判断是否方法名包含s,如果包含返回true
 * 方法对象.getParameterCount() 该方法会返回方法所含参数的个数
 * 方法对象.getModifiers() 如果该方法的返回值是 Modifier.PUBLIC,说明方法是公开方法
 * 4)调用方法即可
 */
public class Test01 {
    public static void main(String[] args) throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            InvocationTargetException {
        Class cls = Class.forName("day20230527.Person");
        Object o = cls.newInstance();
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            //System.out.println(declaredMethod);
            if (declaredMethod.getName().contains("s") &&
                    declaredMethod.getParameterCount() == 0 &&
                    declaredMethod.getModifiers() == Modifier.PUBLIC) {
                System.out.println("自动执行Person类中:" + declaredMethod.getName() + "方法");
                declaredMethod.invoke(o);
            }
        }
    }
}
