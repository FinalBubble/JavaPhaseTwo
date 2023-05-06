package day20230506;

import java.util.ArrayList;
import java.util.Collection;

public class Demo01 {
    public static void main(String[] args) {
        Collection c = new ArrayList();
        Point p = new Point(1, 2);
        c.add(p);
        System.out.println(p);//(1,2)
        System.out.println(c);//[(1,2)]
        p.setX(2);
        System.out.println(p);//(2,2)
        System.out.println(c);//[(2,2)]
    }
}
