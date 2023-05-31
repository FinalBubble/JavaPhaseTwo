package day20230528;

import day20230527.Person;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

/**
 * 自动调用被AutoRunMethod修饰的方法
 */
public class TestAnnotation05 {
    public static void main(String[] args) throws ClassNotFoundException, URISyntaxException, InstantiationException, IllegalAccessException, InvocationTargetException {
        File dir = new File(
                Person.class.getResource(".").toURI()
        );
        File[] files = dir.listFiles(f -> f.getName().endsWith(".class"));
        for (File file : files) {
            //String fileName = file.getName();
            //String className = fileName.substring(0,fileName.indexOf('.'));
            //String packageName = Person.class.getPackageName();
            //String path = packageName + "." + className;
            //Class cls = Class.forName(path);
            Class cls = Class.forName(
                    Person.class.getPackage().getName() +
                            "." +
                            file.getName().substring(0,file.getName().indexOf('.'))
            );
            if (cls.isAnnotationPresent(AutoRunClass.class)) {
                System.out.println(cls.getSimpleName() + "被AutoRunClass注解修饰了，可以实例化");
                Object o = cls.newInstance();
                //System.out.println(o);
                Method[] declaredMethods = cls.getDeclaredMethods();
                for (Method declaredMethod : declaredMethods) {
                    if (declaredMethod.isAnnotationPresent(AutoRunMethod.class)){
                        //获取注解传入的参数值
                        //1、获取方法上的注解
                        AutoRunMethod autoRunMethod = declaredMethod.getAnnotation(AutoRunMethod.class);
                        //2、通过注解实例，获取参数值
                        int value = autoRunMethod.num();
                        System.out.println("自动调用方法："+declaredMethod.getName()+ " : "+ value +"次");
                        for (int i = 0; i < value; i++) {
                            declaredMethod.invoke(o);
                        }
                    }
                }
            }
        }
    }
}
