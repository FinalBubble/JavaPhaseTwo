package day20230504;

/**
 * 死锁
 * 当多个线程都各自持有一个锁,同时等待对方先释放锁时,
 * 会形成一种僵持状态,这个状态我们称为死锁现象
 * 共用一套餐具(一副筷子,一个勺)
 * 北方人:先吃饭后喝汤
 * 南方人:先喝汤后吃饭
 * 阶段1:北方人拿筷子吃饭,南方人拿勺喝汤
 * 阶段2:北方人拿勺喝汤,南方人拿筷子吃饭
 */
public class DeadLockDemo {
    static Object chopsticks = new Object();//一副筷子
    static Object spoon = new Object();//一个勺

    public static void main(String[] args) {
        Thread t1 = new Thread("北方人"){
            @Override
            public void run() {
                try {
                    System.out.println(getName() + "开始吃饭...");
                    System.out.println(getName() + "拿起了筷子,开始吃饭...");
                    //将chopsticks当做同步监视器对象,模拟北方人拿起了筷子
                    synchronized (chopsticks) {
                        Thread.sleep(5000);
                        System.out.println(getName() + "吃完饭,准备喝汤,去拿勺...");
                        //将spoon当做同步监视器对象,模拟北方人拿起了勺
                    }
                    synchronized (spoon) {
                        System.out.println(getName() + "拿起了勺,开始喝汤...");
                        Thread.sleep(5000);
                    }
                    System.out.println(getName() + "喝完汤,放下了勺...");
                    System.out.println(getName() + "放下了筷子,吃饭完毕!!!");
                } catch (Exception e) {

                }
            }
        };
        Thread t2 = new Thread("南方人") {
            //模拟南方人吃饭的过程
            public void run() {
                try {
                    System.out.println(getName() + "开始吃饭...");
                    System.out.println(getName() + "拿起了勺,开始喝汤...");
                    //将spoon当做同步监视器对象,模拟南方人拿起了勺
                    synchronized (spoon) {
                        Thread.sleep(5000);
                        System.out.println(getName() + "喝完汤,准备吃饭,去拿筷子...");
                        //将chopsticks当做同步监视器对象,模拟南方人拿起了筷子
                    }
                    synchronized (chopsticks) {
                        System.out.println(getName() + "拿起了筷子,开始吃饭...");
                        Thread.sleep(5000);
                    }
                    System.out.println(getName() + "吃完饭,放下了筷子...");
                    System.out.println(getName() + "放下了勺,吃饭完毕!!!");
                } catch (Exception e) {

                }
            }
        };
        t1.start();
        t2.start();
    }
}
