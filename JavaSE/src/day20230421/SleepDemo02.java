package day20230421;

/**
 * sleep方法要求必须处理中断异常
 * 当一个线程调用sleep方法处于睡眠阻塞中,此时该线程的interrupt方法被调用时,
 * 会立即中断睡眠阻塞,并会抛出中断异常
 */
public class SleepDemo02 {
    public static void main(String[] args) {
        Thread t1 = new Thread("张三"){
            @Override
            public void run() {
                System.out.println("睡觉");
                try {
                    Thread.sleep(10*1000);
                    System.out.println(getName()+"睡醒了");
                } catch (InterruptedException e) {
                    System.out.println("睡觉被吵醒");
                }
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                System.out.println("砸墙");
                for (int i = 0; i < 5; i++) {
                    System.out.println(getName()+":80");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(getName()+"砸完");
                t1.interrupt();//强制中断t1线程的睡眠阻塞

            }
        };
        t1.start();
        t2.start();
    }
}
