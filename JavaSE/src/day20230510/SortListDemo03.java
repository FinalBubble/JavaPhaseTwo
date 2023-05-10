package day20230510;
import day20230506.Point;

import java.util.*;
/**
 * 自定义排序
 */
public class SortListDemo03 {
    public static void main(String[] args) {
        List<Point> list = new ArrayList<>();
        list.add(new Point(1, 2));
        list.add(new Point(4, 7));
        list.add(new Point(12, 21));
        list.add(new Point(2, 7));
        list.add(new Point(4, 0));
        System.out.println(list);
        /*
         * Collections.sort(List list) 对传入的集合进行排序
         * 但是要求传入的List必须实现了Comparable接口,
         * 实现了该接口的类必须要重写compareTo用于定义比较大小的规则,
         * 从而sort方法可以实现排序功能
         * 这种方式具有侵入性
         * 所以可以使用另外一种方式,在调用sort方法时,
         * 再传入一个比较器Comparator,该比较器必须重写compare方法,
         * 定义比较规则
         * compare方法返回值是一个整数值
         * 如果返回值>0 说明第一个参数大于第二个参数
         * 如果返回值<0 说明第一个参数小于第二个参数
         * 如果返回值=0 说明两个参数相等
         */
        Collections.sort(list, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int len1 = o1.getX() * o1.getX() + o1.getY() * o1.getY();
                int len2 = o2.getX() * o2.getX() + o2.getY() * o2.getY();
                return len1-len2;
            }
        });
        //Collections.reverse(list);//反转
        System.out.println(list);
    }
}
