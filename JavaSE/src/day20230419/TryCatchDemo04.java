package day20230419;

/**
 * 捕获多个异常,但是异常的出现很随机,所以可以有一个兜底的方案
 * 先捕获子类异常
 * 再捕获父类异常
 */
public class TryCatchDemo04 {
    public static void main(String[] args) {
        System.out.println("程序开始了");
        try {
            String s = "a";
            //程序执行这句代码时,一定会出现空指针异常
            System.out.println(s.length());
            //charAt(int index) 获取字符串中对应下标的字符
            System.out.println(s.charAt(0));
            //Integer.parseInt(str) 将字符串转换为整数(字符串必须由整数组成)
            System.out.println(Integer.parseInt(s));
            //当出现异常,后面的代码不会执行
            System.out.println("哈哈哈");
        }catch (NullPointerException | StringIndexOutOfBoundsException e){
            System.out.println("出现了空指针或者是字符串下标越界异常,并在这里得到了解决");
        }catch (Exception e){
            System.out.println("无论出现了什么异常,都在这里能得到解决");
        }

        System.out.println("程序结束了");
    }
}
