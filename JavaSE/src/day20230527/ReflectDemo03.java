package day20230527;

import java.lang.reflect.Method;

/**
 * 获取类中的所有方法
 */
public class ReflectDemo02 {
    public static void main(String[] args) {
        /**
         * 学习反射,首先要学习一个类: Class类对象
         * 这个类比较特殊,一般情况下,我们是不会去new这个类的
         * 但是这个类的实例是比较重要
         * Class的每一个实例用于表示JVM中加载的一个类,
         * 并且每个别JVM加载的类都有且仅有一个Class的实例,
         * 该Class的实例,会记录所表示的类的一切信息:类名,包名,有哪些构造器,方法属性等等.
         * 因此反射操作的第一步就是获取要操作的类的类对象,获取一个类的类对象方式有:
         * 1: 类名.class
         *  Class cls = String.class;
         *  Class cls = ReflectDemo1.class;
         *  Class cls = int.class;(基本类型获取Class实例只能通过这种方式)
         *  第一种方式有一个缺点:就是写死了,所以用的比较少.一般用于方法的传参
         * 2: Class.forName(String className)
         *  使用Class类的静态方法forName,传入一个要加载的类的全路径(包名.类名)
         *  Class cls = Class.forName("java.lang.String");
         *  Class cls = Class.forName("reflect.ReflectDemo1");
         */
        //获取String的Class实例，该实例中，记录了String类中的所有信息
        Class cla = String.class;
        //获取当前类对象所表示的类中的所有方法(包含私有方法,不包含从超类中继承的方法)
        Method[] methods = cla.getDeclaredMethods();
        System.out.println(cla.getSimpleName()+"类中一共有:"+methods.length+"个方法");
        for (Method method : methods) {
            System.out.println(cla.getSimpleName()+"类中的方法: "+method.getName());
        }
    }
}
