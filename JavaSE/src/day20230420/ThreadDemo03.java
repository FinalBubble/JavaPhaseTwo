package day20230420;

/**
 * 使用匿名内部类完成两种线程的创建方式
 */
public class ThreadDemo03 {
    public static void main(String[] args) {
        //直接继承Thread重写run方法的形式
        Thread t1 = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println(555);
                }
            }
        };
        //实现Runnable接口重写run方法单独定义任务的形式
        Runnable r1 = () -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println(666666);
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println(777777777);
            }
        };
        t1.start();
        Thread t2 = new Thread(r1);
        t2.start();
    }
}
