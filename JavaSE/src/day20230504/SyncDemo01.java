package day20230504;


/**
 * 多线程并发安全问题
 * 当多个线程并发操作同一个临界资源,由于线程切换的不确定性,导致操作顺序出现混乱,
 * 而引起的各种逻辑错误
 * 临界资源:操作该资源的完成过程同一时刻只能由单线程进行
 */
public class SyncDemo01 {
    public static void main(String[] args) {
        Table table = new Table();
        Thread t1 = new Thread("A1A") {
            public void run() {
                while (true) {
                    int bean = table.getBean();
                    System.out.println(getName() + "抢了第" + (20 - bean + 1) + "颗豆子,桌子还剩" + (bean - 1) + "颗");
                }
            }
        };
        Thread t2 = new Thread("B2B") {
            public void run() {
                while (true) {
                    int bean = table.getBean();
                    System.out.println(getName() + "抢了第" + (20 - bean + 1) + "颗豆子,桌子还剩" + (bean - 1) + "颗");
                }
            }
        };
        t1.start();
        t2.start();
    }

}
class Table{
    private int beans = 20;//桌子上有20颗豆子

    //取桌子上的一颗豆子

    /*
     * 当一个方法加上synchronized关键字,该方法成为同步方法,
     * 如果多个线程要抢这个方法,但是同一时间,该方法只能被一个线程调用,
     * 也就是当一个线程执行完该方法后,其余线程才能继续执行
     * 将多线程并发安全问题,改为同步方法,有先后顺序执行,可以有效的解决并发安全问题
     * 同步方法的同步监视器对象默认是this
     */
    public synchronized int getBean(){
        if (beans == 0) {
            throw new RuntimeException("桌子上没有豆子了!");
        }
        Thread.yield();//礼让线程,为了增加出现线程安全问题的可能性
        return beans--;
    }
}