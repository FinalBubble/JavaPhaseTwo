package day20230419;


/**
 * 异常的抛出
 * throw关键字可以主动对外抛出一个异常
 * 通常下列情况我们需要主动抛出异常:
 * 1:当前代码片段出现了异常,但是该异常不应该在当前代码片段被解决时,可以将其抛出
 * 2:程序可以运行,但是不满足业务要求时,可以对外抛出一个异常告知(满足语法但是不满足业务)
 */
public class ThrowDemo {
    public static void main(String[] args) {
        Person p = new Person();
        try {
            p.setAge(20000);//满足语法但是不满足业务
        } catch (IllegalAgeException e) {
            e.printStackTrace();
        }
        System.out.println("此人年龄："+p.getAge());
    }
}
