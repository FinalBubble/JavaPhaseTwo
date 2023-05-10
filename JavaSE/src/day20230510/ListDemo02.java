package day20230510;

import java.util.ArrayList;
import java.util.List;

/**
 * List重载了一对add,remove方法
 */
public class ListDemo02 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        System.out.println(list);
        /*
         * void add(int index,E e)
         * 将给定的元素e插入到指定的index位置处
         * 此处插入值后,该位置原元素往后移动一位
         */
        //将F插入到C之后
        list.add(3,"F");
        System.out.println(list);
        /*
         * E remove(int index)
         * 删除并返回指定index位置的元素
         */
        String str = list.remove(4);
        System.out.println(list);
        System.out.println(str);
    }
}
