package day20230510;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组转换为集合
 * 数组的工具类Arrays,其中提供了一个静态方法asList
 */
public class ArrayToListDemo {
    public static void main(String[] args) {
        String[] array = {"a", "b", "c", "d", "e"};
        System.out.println("数组:" + Arrays.toString(array));
        List<String> list = Arrays.asList(array);
        System.out.println("集合:"+list);
        //对该集合的操作就是对原数组的操作
        list.set(1, "f");
        System.out.println("集合:"+list);
        System.out.println("数组:" + Arrays.toString(array));
        /*
         * 由于现在转换的集合和数组是命运共同体,由于数组是定长的,
         * 给集合添加新的元素,就会导致给数组也添加新的元素,
         * 那就会发生异常
         */
        //list.add("g");
        /*
         * 如果有对集合的增删的需求,可以创建一个新的集合,集合中的元素就是原集合的元素
         * 所有集合支持一个参数为Collection的构造器,目标是创建集合的同时,
         * 包含给定集合的所有元素
         */
        List<String> list1 = new ArrayList<>(list);
        list1.add("g");
        System.out.println("集合:"+list1);
    }
}
