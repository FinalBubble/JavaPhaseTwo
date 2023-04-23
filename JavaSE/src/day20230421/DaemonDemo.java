package day20230421;

/**
 * 守护线程
 * java将线程分为两类:用户线程和守护线程,也称为前台线程和后台线程
 * 守护线程是通过普通的用户线程调用setDaemon(true)设置而转变来的
 * 因此创建和使用上,守护线程和用户线程并没有区别,但是主要的区别在于:
 * 当进程的结束,意味着进程中所有的用户线程都结束了,那么此时守护线程
 * 也会随着一起被全部杀死
 */
public class DaemonDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread("rose"){
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(getName()+":let me go!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(getName()+":没人救");
                System.out.println("噗通！");
            }
        };
        Thread t2 = new Thread("jack"){
            @Override
            public void run() {
                while (true){
                    System.out.println(getName()+":you jump,I jump");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
        //将t2设置为守护线程,当进程结束时,t2也会被销毁
        t2.setDaemon(true);
        t2.start();
    }
}
