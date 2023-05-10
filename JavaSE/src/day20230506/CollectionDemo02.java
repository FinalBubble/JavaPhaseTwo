package day20230506;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * 元素会影响集合操作的相关方法
 */
public class CollectionDemo02 {
    public static void main(String[] args) {
        Collection c = new ArrayList();//该集合允许存储重复的元素
        //Collection c = new HashSet();//该集合不允许存储重复的元素
        c.add(new Point(1, 2));
        c.add(new Point(1, 2));
        c.add(new Point(1, 2));
        c.add(new Point(1, 2));
        c.add(new Point(3, 4));
        c.add(new Point(5, 6));
        c.add(new Point(7, 8));
        c.add(new Point(9, 0));
        /*
         * 集合输出的格式:
         * [元素1.toString(),元素2.toString(),元素3.toString(),...]
         * 需要重写toString方法,输出自己需要的格式
         */
        System.out.println(c);//[(1,2), (3,4), (5,6), (7,8), (9,0)]
        Point p = new Point(1, 2);
        /*
         * boolean contains(Object o)
         * 判断当前集合是否包含给定元素
         * 元素是否包含取决于该元素是否与集合中现有的元素存在equals比较为true的情况
         * 有的时候,不希望比较的是内存地址,所以可以重写equals方法
         */
        //判断集合c中是否包含p点
        boolean contains = c.contains(p);
        System.out.println("c集合中是否包含p点:" + contains);
        /*
         * remove方法,删除集合中给定的元素
         * 也是利用equals比较,结果为true的就进行删除,但是如果集合中包含重复的元素,仅会删除一次
         */
        c.add(p);
        System.out.println(c);
        //删除集合c中包含的p点,但是只会删除一次
        c.remove(p);
        System.out.println(c);
    }
}
