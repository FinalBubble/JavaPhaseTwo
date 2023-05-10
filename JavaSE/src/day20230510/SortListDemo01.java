package day20230510;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 集合的排序
 * 集合的工具类java.util.Collections 提供了一个静态方法sort,
 * 可以对List集合进行自然排序
 */
public class SortListDemo01 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();//生成随机数
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(100));
        }
        System.out.println(list);
        Collections.sort(list);//自然排序 从小到大
        System.out.println(list);
    }
}
