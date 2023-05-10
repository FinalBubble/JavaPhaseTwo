package day20230509;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 集合是支持随机访问(可以访问任意位置的元素)
 * 但是在Collection层面而言,是做不到随机访问的
 * 但是集合Collection要求所有的集合支持遍历操作,我们可以通过遍历的方式最终拿到每一个元素
 * 集合提供了一个统一遍历方式:迭代器遍历模式
 * 对应方法:Iterator iterator();
 * 该方法会返回用于遍历当前集合的迭代器
 */
public class IteratorDemo {
    public static void main(String[] args) {
        Collection c = new ArrayList();
        c.add("A");
        c.add("#");
        c.add("B");
        c.add("#");
        c.add("C");
        c.add("#");
        c.add("D");
        c.add("#");
        c.add("E");
        System.out.println(c);
        //遍历集合c
        //1.获取集合的迭代器,这个迭代器具有遍历当前集合的功能
        Iterator iterator = c.iterator();
        //2.问:当前集合是否有下一个元素
        /*
         *  boolean hasNext()
         *  判断集合中是否还有下一个元素可以遍历
         *  简单理解:初始迭代器的位置在集合第一个元素之前.
         *  也就是利用该方法判断集合中迭代器当前指向位置是否还有下一个元素可以遍历
         */
        while (iterator.hasNext()){
            //3.取:取出下一个元素
            /*
             * E next()
             * 取出集合中的下一个元素,获取后,迭代器的位置会向后移动一个位置
             */
            String s = (String) iterator.next();
            System.out.println(s);
            if ("#".equals(s)){
                //4.删:删除集合遍历的当前元素(但是不能调用集合中的删除方法)
                //c.remove(e);
                //迭代器提供了可以在遍历过程中删除集合元素的方法
                iterator.remove();
            }
        }
        System.out.println(c);
    }
}
