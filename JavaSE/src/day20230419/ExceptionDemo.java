package day20230419;

public class ExceptionDemo {
    public static void main(String[] args) {
        /*
        * Exception in thread "main" java.lang.ArithmeticException: / by zero
        *                main 线程中的异常        数学异常               原因
        * at day20230419.ExceptionDemo.main(ExceptionDemo.java:6)
        *                                                异常出现的位置：第6行
        * */
        int i = 1/0;
    }
}
