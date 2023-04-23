package day20230421;


import java.util.Scanner;

/**
 * 线程提供了一个静态的方法:
 * static void sleep(long ms)
 * 当一个线程调用了该方法就会进入到阻塞状态,持续指定的毫秒值,
 * 当时间过去,线程会自动从阻塞状态转变为就绪状态
 * 运行状态--sleep()-->阻塞状态--时间结束-->就绪状态--分配CPU时间片-->运行状态
 */
public class SleepDemo {
    public static void main(String[] args) {
        System.out.println("程序开始了！");
        /*
         * 倒计时程序
         * 启动程序后,让用户输入指定的倒计时时间
         * 程序开始从指定的数字每秒输出数字递减.到0时,退出程序
         */
        System.out.println("输入倒计时时间：");
        int time = new Scanner(System.in).nextInt();

        try {
            for (int i = time; i > 0; i--) {
                System.out.println(i);
                Thread.sleep(1000);
            }
            System.out.println("时间到！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("程序结束了！");
    }
}
