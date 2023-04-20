package day20230420;

/**
 * java中所有的代码都是靠线程运行的,main方法也一样,main方法是靠"main线程"执行的
 * JVM启动后,就会自动创建一个main线程,用来执行main方法,这个线程我们通常称为"主线程",
 * 但是主线程和我们自己创建的线程没有什么区别
 * Thread类中提供了一个静态方法,可以帮助我们获取执行该方法的线程对象
 * static Thread currentThread()
 */
public class CurrentThreadDemo {
    public static void main(String[] args) {
        Thread main = Thread.currentThread();
        //Thread[main,5,main] 线程名,优先级,线程组
        System.out.println(main);
        Runnable r1 = new Runnable(){
            @Override
            public void run() {
                Thread t = Thread.currentThread();
                System.out.println(t);
            }
        };
        Thread t1 = new Thread(r1,"t1线程");
        Thread t2 = new Thread(r1,"t2线程");
        t1.start();
        t2.start();
    }
}
