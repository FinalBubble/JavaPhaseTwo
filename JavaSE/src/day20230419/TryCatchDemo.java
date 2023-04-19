package day20230419;

/**
 * try{
 * 可能会出现异常的代码
 * }catch(异常类型 变量名){
 * 当try中出现异常,此时异常处理代码
 * }
 * 此案例讲解try...catch语法
 */
public class TryCatchDemo {
    public static void main(String[] args) {
        System.out.println("程序开始了");
        try {
            String s = null;
            //程序执行这句代码时,一定会出现空指针异常
            System.out.println(s.length());
            //当出现异常,后面的代码不会执行
            System.out.println("哈哈哈");
        }catch (NullPointerException e){
            System.out.println("出现了空指针,并在这里得到了解决");
        }

        System.out.println("程序结束了");
    }
}
