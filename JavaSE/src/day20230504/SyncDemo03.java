package day20230504;

public class SyncDemo03 {
    public static void main(String[] args) {
        Foo foo = new Foo();
        Thread t1 = new Thread("小花"){
            @Override
            public void run() {
                foo.methodA();
            }
        };
        Thread t2 = new Thread("小红"){
            @Override
            public void run() {
                foo.methodB();
            }
        };
        t1.start();
        t2.start();

    }
}

class Foo{
    public synchronized void methodA(){
        Thread t = Thread.currentThread();//获取可以执行该方法的线程对象
        System.out.println(t.getName()+":正在执行A方法...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.getName()+":A方法执行完毕!!!");
    }
    public synchronized void methodB(){
        Thread t = Thread.currentThread();
        System.out.println(t.getName()+":正在执行B方法...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.getName()+":B方法执行完毕!!!");
    }
}
