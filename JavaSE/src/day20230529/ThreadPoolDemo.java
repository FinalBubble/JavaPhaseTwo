package day20230529;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * 线程池是线程的管理机制，它主要解决两个问题
 * 1、控制线程的数量
 * 2、复用线程
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        //创建一个固定容量的线程池,容量为2
        ExecutorService pool = Executors.newFixedThreadPool(2);
        //创建5个任务
        for (int i = 0; i < 5; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    Thread thread = Thread.currentThread();
                    System.out.println(thread.getName() + ":正在执行任务！");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(thread.getName() + ":执行任务结束！");
                }
            };
            //将任务交给线程池，由线程池分配执行任务的线程
            pool.execute(runnable);
            System.out.println("将一个任务交给了线程池！");
        }
        //pool.shutdown();//关闭线程池，会将交给线程池执行的任务执行完毕后，再关闭
        pool.shutdownNow();//立即关闭线程池，即使线程池中存有没有执行的任务，也会强制关闭
        System.out.println("线程池关闭了");
    }

}
