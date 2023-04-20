package day20230420;


/**
 * 线程第二种创建方式:
 * 1:构建一个类,让该类实现Runnable接口,一般叫做线程的任务类
 * 2:实现该接口,要求必须重写run方法
 * 3:将线程要执行的任务代码写在run方法中
 * 4:实例化线程的任务类对象
 * 5:创建线程实例对象
 * 6:将线程要执行的任务类对象传给线程对象
 * 7:调用start方法启动线程
 * 优点:实现了Runnable接口,java中接口是多实现,并且没有占用继承的位置
 */
public class ThreadDemo02 {
    public static void main(String[] args) {
        MyRunnabble1 myRunnabble1 = new MyRunnabble1();
        MyRunnabble2 myRunnabble2 = new MyRunnabble2();
        Thread thread1 = new Thread(myRunnabble1);
        Thread thread2 = new Thread(myRunnabble2);
        thread1.start();
        thread2.start();
    }
}

class MyRunnabble1 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(333);
        }
    }
}

class MyRunnabble2 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(444444);
        }
    }
}