package day20230510;

import java.util.ArrayList;
import java.util.List;

/**
 * List中提供的方法
 * List subList(int start,int end)
 * 获取当前集合中指定范围的子集
 */
public class ListDemo03 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);
        //获取子集[3-7] 含头不含尾
        List<Integer> subList = list.subList(3, 8);
        System.out.println(subList);
        //将子集中的每个元素都扩大10倍
        for (int i = 0; i < subList.size(); i++) {
            subList.set(i, subList.get(i) * 10);
        }
        System.out.println(subList);
        /*
         * 对子集的操作就是对原集合对应元素的操作
         */
        System.out.println(list);
        //清空集合中1-8这部分元素
        list.subList(1, 9).clear();
        System.out.println(list);
        
    }
}
