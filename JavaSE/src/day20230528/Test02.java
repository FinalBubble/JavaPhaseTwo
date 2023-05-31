package day20230528;


import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;

/**
 * 自动调用与当前类Test2同一个包下的所有类中,方法名含有s的无参公开方法
 */
public class Test02 {
    public static void main(String[] args) throws URISyntaxException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        //定位Test2所在的目录(包)
        /*
         *  Test2.class.getResource(".").toURI()
         *  "."表示的是当前类Test2所在编译目录的目录
         *  D:\Dates\IDEASPACE\VN2206SE\out\production\VN2206SE\reflect
         *  Test2.class.getClassLoader().getResource(".").toURI()
         *  "."表示的是当前类Test2所在的编译目录的所在的包的顶级包的上一层目录
         *  D:\Dates\IDEASPACE\VN2206SE\out\production\VN2206SE
         */
        File Package = new File(
                Test02.class.getResource(".").toURI()
        );
        File[] files = Package.listFiles( f -> f.getName().endsWith(".class"));
        for (File file : files) {
            String fileName = file.getName();
            //substring(int start) 表示从下标start的位置截取字符串,保留从下标start开始的字符串
            //substring(int start,int end) 表示截取下标[start,end)部分的字符串
            //indexOf(String reg) 获取字符串中出现指定字符串的下标
            String packageName = Test02.class.getPackage().getName();
            String className = fileName.substring(0,fileName.indexOf('.'));
            String path = packageName + "." + className;
            //System.out.println(path);
            Class aClass = Class.forName(path);
            Object o = aClass.newInstance();
            Method[] methods = aClass.getDeclaredMethods();
            //System.out.println(methods);
            for (Method method : methods) {
                System.out.println(method.getName());
                if (method.getName().contains("s") &&
                        method.getParameterCount() == 0 &&
                        method.getModifiers() == Modifier.PUBLIC) {
                    System.out.println("自动执行" + className + "类中:" + method.getName() + "方法");
                    method.invoke(o);
                }
            }
        }
    }
}
