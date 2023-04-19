package day20230419;

public class FinallyDemo {
    public static void main(String[] args) {
        System.out.println("程序开始了");
        //ctrl+alt+T 快速生成代码块
        try {
            String s = null;
            System.out.println(s.length());
        } catch (Exception e) {
            System.out.println("出错了,在这里得到了解决!");
        } finally {
            System.out.println("这部分的代码一定会被执行!");
        }
        System.out.println("程序结束了");
    }
}
