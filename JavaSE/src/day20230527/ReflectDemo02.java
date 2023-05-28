package day20230527;

import java.lang.reflect.Method;

/**
 * java反射机制
 * 反射是java中的动态机制,它允许我们在程序的运行期间再确定对象的实例化,方法的调用,
 * 属性的操作等等.使得程序的灵活度大大提升,但是同时也会带来更多的资源消耗和较低的运行效率
 */
public class ReflectDemo01 {
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
        //获取String类的相关信息
        String name = cla.getName();//获取包名.类名
        System.out.println("全路径名："+name);
        name = cla.getSimpleName();//获取类名
        System.out.println("类名："+name);
        name = cla.getPackage().getName();//获取包名
        System.out.println("包名："+name);
        //获取当前类对象中所表示的类的所有的公开方法(包括从父类中继承的方法)
        Method[] methods = cla.getMethods();
        System.out.println(cla.getSimpleName()+"类中一共有"+methods.length+"个公有方法!");
        for (Method method : methods) {
            System.out.println(cla.getSimpleName()+"类中的公有方法: "+method.getName());
        }
    }
}
