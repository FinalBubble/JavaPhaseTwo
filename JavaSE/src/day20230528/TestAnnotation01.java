package day20230528;

/**
 * Person类有被AutoRunClass修饰
 */
public class TestAnnotation01 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class cls = Class.forName("day20230527.Person");
        /**
         * isAnnotationPresent 判断是否被指定的注解修饰
         * 除了Class之外,像Method,Filed很多其他的反射对象也支持isAnnotationPresent方法,
         * 就是分别判断方法上,属性上,是否被注解修饰了
         */
        if (cls.isAnnotationPresent(AutoRunClass.class)) {
            System.out.println(cls.getName() + "被AutoRunClass注解修饰了");
        } else {
            System.out.println(cls.getName() + "没有被AutoRunClass注解修饰了");
        }
    }
}
