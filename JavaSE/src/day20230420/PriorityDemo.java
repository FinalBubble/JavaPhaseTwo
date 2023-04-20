package day20230420;


/**
 * 线程的优先级
 * 线程有10个优先级,分别对应的整数1-10,其中1是最低的优先级,10是最高的优先级,
 * 如果线程创建之后,没有专门去设置优先级,默认优先级是5
 * 本身线程的调度是尽可能的公平的分配时间片,但是有时候可能需要执行的次数多一些,
 * 那么可以设置优先级,但是,现在这个优先级不太准了,因为多核心的时代下,优先级的概念越来越模糊了
 *
 */
public class PriorityDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread("第一个测试线程"){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(getName()+"启动了");
                }
            }
        };

        Thread t2 = new Thread("第二个测试线程"){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(getName()+"启动了");
                }
            }
        };

        Thread t3 = new Thread("第三个测试线程"){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(getName()+"启动了");
                }
            }
        };

        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t3.setPriority(Thread.NORM_PRIORITY);
        t1.start();
        t2.start();
        t3.start();
    }
}
