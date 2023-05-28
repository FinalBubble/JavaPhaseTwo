package day20230528;

import java.util.Arrays;

/**
 * JDK5之后，推出的特性，变长参数
 */
public class ArgsDemo01 {
    public static void main(String[] args) {
        doing(1,2,3,4,5,6,7,8);
    }

    /**
     * 变长参数，只能作为方法中最后一个参数声明
     * 可变参数在一个方法中只能使用一次
     * @param a int... a 相当于声明了一个数组
     */
    public static void doing(int... a){
        System.out.println(a);
        System.out.println(Arrays.toString(a));
    }
    public static void doing2(String name,int... a){
        System.out.println(a);
        System.out.println(Arrays.toString(a));
    }
}
