package day20230510;

import java.util.ArrayList;
import java.util.List;

/**
 * java.util.List接口
 * List继承自Collection,是最常用的一类集合 特点:可以存放重复元素且有序
 * List里面提供了一套可以通过下标操作元素的方法
 * 常用的实现类:
 * java.util.ArrayList 内部使用的是数组实现,查询性能更好
 * java.util.LinkedList 内部使用链表实现,增删性能更好,尤其是首尾性能最佳
 * 对性能没有特别苛刻的要求,通常使用ArrayList
 */
public class ListDemo01 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        System.out.println(list);
        //获取集合中第三个元素
        /*
         * E get(int index)
         * 获取指定index位置的元素
         * 集合中index的起始值是0
         * 所以想要获取集合中第n个元素,index的值就是n-1
         */
        String s = list.get(2);
        System.out.println(s);
        //List集合支持下标遍历 list.fori
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
        }
        System.out.println();
        /*
         *  E set(int index,E e)
         * 将给定的元素e设置到指定的index位置上,返回值为index位置上原来的元素
         * 替换元素操作
         */
        //将list集合中index值为1的元素,替换成"F"
        s = list.set(1, "F");
        System.out.println(list);
        System.out.println(s);
    }
}
