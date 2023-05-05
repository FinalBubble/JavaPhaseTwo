package day20230504;

/**
 * 同步代码块
 * 有效的缩小同步范文,可以在保证并发安全的前提下,尽可能的提高并发效率
 * 语法:
 * synchronized(同步监视器对象){
 * 需要多个线程同步执行的代码片段
 * }
 */
public class SyncDemo02 {
    public static void main(String[] args) {
        Shop shop = new Shop();
        Shop shop1 = new Shop();
        Thread t1 = new Thread("张壮") {
            public void run() {
                shop.buy();
            }
        };
        Thread t2 = new Thread("玉杰") {
            public void run() {
                shop1.buy();
            }
        };
        t1.start();
        t2.start();
    }
}
class Shop{
    public void buy(){

        try {
            Thread t = Thread.currentThread();
            System.out.println(t.getName() + ":正在挑衣服...");
            Thread.sleep(5000);
            /*
             * 同步块在使用时需要在"()"中指定同步监视器对象,该对象可以是任意的引用类型的实例,
             * 只要保证多个线程需要同步执行该同步代码块的线程看到的这个对象是同一个即可,
             * 实际开发中,要根据实际情况决定
             * 此处的this指的是当前方法buy方法的调用者,也就是shop
             * 而shop对于两个线程而言,是同一个
             */
            synchronized (this){
                System.out.println(t.getName() + ":正在试衣服...");
                Thread.sleep(5000);
            }

            System.out.println(t.getName() + ":结账离开!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
