package day20230510;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * 集合转换为数组
 * Collection中定义了方法toArray
 */
public class CollectionToArrayDemo {
    public static void main(String[] args) {
        Collection<String> c = new ArrayList<>();
        c.add("one");
        c.add("two");
        c.add("three");
        c.add("four");
        c.add("five");
        System.out.println("集合:" + c);
        //toArray() 由于返回的是Object类型的数组,所以用的很少
        //Object[] array = c.toArray();
        //toArray(T[] arr) T是什么类型,就会返回什么类型的数组
        //一般转换的集合元素的个数就是数组的长度
        String[] array = c.toArray(new String[c.size()]);
        //Arrays 是数组工具类
        System.out.println("数组:"+ Arrays.toString(array));
    }
}
