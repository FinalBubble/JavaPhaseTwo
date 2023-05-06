package day20230506;

import java.util.ArrayList;
import java.util.Collection;

public class Demo02 {
    public static void main(String[] args) {
        String s = "hello";
        int a = 1;
        Point p = new Point(1, 2);
        Collection c = new ArrayList();
        c.add(p);
        test(s, a, p, c);
        System.out.println("s:" + s);//? s:hello
        System.out.println("a:" + a);//? a:1
        System.out.println("p:" + p);//? p:3,2
        System.out.println("c:" + c);//? c:[p]
    }

    public static void test(String s, int a, Point p, Collection c) {
        a++;
        s = s + "world";
        p.setX(3);
        p = new Point(4, 5);
        c.clear();
        c.add(p);
        c = new ArrayList();
        p.setX(7);
        c.add(p);
    }
}
