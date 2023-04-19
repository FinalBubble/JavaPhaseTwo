package day20230419;

/**
 * 捕获多个异常,不同异常有相同的处理方案,可以合并捕获异常
 */
public class TryCatchDemo03 {
    public static void main(String[] args) {
        System.out.println("程序开始了");
        try {
            String s ="";
            //程序执行这句代码时,一定会出现空指针异常
            System.out.println(s.length());
            //charAt(int index) 获取字符串中对应下标的字符
            System.out.println(s.charAt(0));
            //当出现异常,后面的代码不会执行
            System.out.println("哈哈哈");
        }catch (NullPointerException | StringIndexOutOfBoundsException e){
            System.out.println("出现了空指针或者是字符串下标越界异常,并在这里得到了解决");
        }

        System.out.println("程序结束了");
    }
}
