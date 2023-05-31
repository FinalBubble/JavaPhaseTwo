package day20230528;

import day20230527.Person;

import java.io.File;
import java.net.URISyntaxException;

/**
 * 包下哪些类有被AutoRunClass修饰
 */
public class TestAnnotation02 {
    public static void main(String[] args) throws ClassNotFoundException, URISyntaxException {
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
                System.out.println(cls.getSimpleName() + "被AutoRunClass注解修饰了");
            } else {
                System.out.println(cls.getSimpleName() + "没有被AutoRunClass注解修饰了");
            }
        }
    }
}
