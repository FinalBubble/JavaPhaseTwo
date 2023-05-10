package day20230509;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 集合间的操作
 */
public class CollectionDemo03 {
    public static void main(String[] args) {
        Collection c1 = new ArrayList();
        c1.add("java");
        c1.add("c++");
        c1.add(".net");
        System.out.println("c1:" + c1);//c1:[java, c++, .net]
        Collection c2 = new ArrayList();
        c2.add("android");
        c2.add("ios");
        System.out.println("c2:" + c2);//c2:[android, ios]
        /*
         * boolean addAll(Collection c)
         * 将给定的集合中的所有元素都添加到当前集合中(取并集)
         * 不会改变给定的集合c
         * 操作集合后,如果发生变化,就会返回true
         */
        c1.addAll(c2);
        System.out.println("c1:" + c1);//[java, c++, .net, android, ios]
        System.out.println("c2:" + c2);//[android, ios]
        Collection c3 = new ArrayList();
        c3.add("c++");
        c3.add("ios");
        //c3.add("php");
        System.out.println("c3:" + c3);//[c++, ios]
        /*
         * boolean containsAll(Collection c)
         * 判断当前集合中是否包含给定的集合中的所有元素
         */
        boolean b = c1.containsAll(c3);
        System.out.println("c1中是否包含c3中所有的元素:" + b);
        /*
         * boolean retainAll(Collection c)
         * 取交集,将两个集合中所共有的元素保留
         */
        c1.retainAll(c3);
        System.out.println("c1:" + c1);//[c++, ios]
        System.out.println("c3:" + c3);//[c++, ios]
        /*
         * 删除交集
         */
        //删除c1中和c3公有的元素,但是c3不受影响
        c1.removeAll(c3);
        System.out.println("c1:" + c1);//[]
        System.out.println("c3:" + c3);//[c++, ios]
    }
}
