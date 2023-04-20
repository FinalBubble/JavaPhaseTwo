package day20230420;


/**
 * 多线程可以并发执行多个任务
 * 线程的第一种创建方式:
 * 1:让一个类继承Thread类,那这个类就是线程的子类
 * 2:在线程类中重写run方法
 * 3:将要该线程类执行的任务代码,放在run方法中
 * 4:启动线程
 *  4.1:创建要启动的线程类对象
 *  4.2:使用线程对象实例调用启动方法start(),千万注意不要调用run方法
 *      启动线程时,一旦调用start方法,会将该线程纳入到进程调度器管理,
 *      一旦该线程被分配到CPU的时间片,该线程就会自动执行run方法中的代码
 * 缺点:由于线程类已经继承了Thread类,而java是单继承特性,所以不能再继承别的类了
 */
public class ThreadDemo01 {
    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        MyThread2 myThread2 = new MyThread2();
        myThread1.start();
        myThread2.start();
    }
}

class MyThread1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(111);
        }
    }
}

class MyThread2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(222222);
        }
    }
}
