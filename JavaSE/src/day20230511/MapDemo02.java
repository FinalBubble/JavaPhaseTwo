package day20230511;

import java.util.*;

/**
 * Map遍历
 * Map支持三种遍历方式:
 * 1:单独遍历key
 * 2:遍历每一组键值对
 * 3:单独遍历value(这个操作不常用)
 */
public class MapDemo02 {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("语文", 100);
        map.put("数学", 90);
        map.put("物理", 80);
        map.put("化学", 60);
        map.put("体育", 120);
        System.out.println(map);
        /*
         * map中存储的key是不允许重复的(Set集合存储不重复的元素)
         * keySet() 当前的map中的所有的key存储进一个Set集合
         */
        Set<String> keySet = map.keySet();
        for (String s : keySet) {
            System.out.println(s);
        }
        System.out.println("================================");
        /*
         * map底层键值对其实是由一个entry对象存储的
         * 也就是获取map中所有的键值对,就是获取所有的entry
         * map中提供了一个方法entrySet()
         * 这个方法可以将map中所有的entry封装到一个Set集合
         */
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        for (Map.Entry<String, Integer> stringIntegerEntry : entrySet) {
            //entry对象提供了getKey方法和getValue方法,获取entry中的key和value
            System.out.println("键:" + stringIntegerEntry.getKey());
            System.out.println("值:" + stringIntegerEntry.getValue());
        }
        System.out.println("========================");
        /*
         * map中的value值是允许重复 (Collection集合可以存储重复元素)
         */
        Collection<Integer> values = map.values();
        for (Integer value : values) {
            System.out.println(value);
        }
        System.out.println("========================");
        /*
         * JDK8之后,集合和Map都支持了基于Lambda表达式的形式进行遍历
         */
        map.forEach(
                //()中第一个形参就是key,第二个形参就是value
                (k,v)-> System.out.println( k + ":" + v )
        );
        Collection<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.forEach(
                //(e)-> System.out.println(e)
                //如果参数只有一个,括号可以省略
                //e -> System.out.println(e)
                //如果输出的参数和传入的参数都是一个,可以省略参数,并且要替换::
                System.out::println
        );
    }
}
