package day20230506;

import java.util.ArrayList;
import java.util.Collection;


/**
 * JAVA集合框架
 * 所有集合的顶级接口: java.util.Collection 这里面定义所有集合都必须具备的功能方法
 * 集合中有两类常用的子类:
 * java.util.List: 可重复的集合
 * java.util.Set: 不可重复的集合
 */
public class CollectionDemo01 {
    public static void main(String[] args) {
        //集合只能存储引用类型
        Collection c = new ArrayList();
        /*
         * add:加
         * boolean add(E e) 这个E是泛型,这里我们先理解为Object
         * 向集合中添加一个元素,如果元素添加成功,则返回true,否则返回false
         */
        c.add(1);//会自动装箱 c.add(Integer.valueOf(1));
        c.add(2);
        System.out.println(c);
        c.add(3);
        System.out.println(c);
        c.add("one");
        c.add(true);
        c.add(new Object());
        System.out.println(c);
        /*
         * 区别于数组的length
         */
        int size = c.size();
        System.out.println("集合的容量:" + size);
        /*
         * 当size=0时,为空集
         */
        boolean empty = c.isEmpty();
        System.out.println("集合是否为空集合:" + empty);
        c.clear();//清空集合,集合的size=0
        System.out.println(c);
        System.out.println("集合的容量:"+c.size());
        System.out.println("集合是否是空集合:"+c.isEmpty());
    }
}
