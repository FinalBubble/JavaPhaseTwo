package day20230528;

import day20230527.Person;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

/**
 * 自动调用被AutoRunMethod修饰的方法
 */
public class TestAnnotation04 {
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
                        System.out.println("自动调用方法："+declaredMethod.getName());
                        declaredMethod.invoke(o);
                    }
                }
            }
        }
    }
}
