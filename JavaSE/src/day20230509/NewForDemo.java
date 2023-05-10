package day20230509;

import java.util.ArrayList;
import java.util.Collection;

/**
 * JDK5推出时,提供了一个新的特性:增强型for循环
 * 通常也被称为新循环或foreach循环
 * 新循环可以方便我们遍历数组和集合的语法是相同的,但是不能取代传统for循环的工作
 * 语法:
 * for(遍历取出的元素类型 e : 遍历的集合或数组){
 * <p>
 * }
 */

public class NewForDemo {
    public static void main(String[] args) {
        String[] arr = {"a", "b", "c", "d", "e"};
        //传统for循环遍历数组 arr.fori
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        //新循环遍历数组 arr.for
        for (String s : arr) {
            System.out.println(s);
        }
        //可以通过在"<>"声明类型,给集合指定转载元素的类型
        /*
         * 这种元素叫做泛型
         * 我们声明集合时,如果指定了泛型类型,
         * 那么整个集合中涉及到E的类型都会变成对应指定的类型
         * 如果不指定泛型,那么E默认就是Object
         */
        Collection<String> c = new ArrayList();
        c.add("A");
        c.add("B");
        c.add("C");
        c.add("D");
        c.add("E");
        //新循环遍历集合 底层实现是迭代器实现
        for (String s : c) {
            System.out.println(s);
        }
    }
}
