package day20230428;

/**
 * 线程提供的方法:join,可以协调线程的同步运行
 * 多线程是并发运行,本身是一种异步运行的状态.
 * 异步运行:各自执行各自的
 * 同步运行:多个线程执行是存在先后顺序的
 * <p>
 * 显示带有图片的网页时,涉及两条线程:
 * 1:下载线程 下载图片
 * 2:显示线程 显示文字,显示图片
 * 最开始,下载线程和显示线程是异步的,各自干各自,
 * 但是当显示线程显示文字结束,显示图片时,发现图片还没有下载完毕,
 * 此时显示线程应该进入阻塞状态,等待图片下载完毕时,再显示图片,此时是同步状态
 */
public class JoinDemo {
    //表示图片是否下载完成,默认是false false表示没下载完成 true表示下载完成
    static boolean isDownLoad = false;
    public static void main(String[] args) {
        Thread t1 = new Thread("下载线程"){
            @Override
            public void run() {
                System.out.println(getName()+":开始下载图片...");
                for (int i = 0; i <= 100; i++) {
                    System.out.println("图片已下载"+i+"%");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(getName()+":图片下载完毕!");
                isDownLoad = true;
            }
        };
        Thread t2 = new Thread("显示线程"){
            @Override
            public void run() {
                System.out.println(getName()+":开始显示文字");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getName()+":文字显示完毕！");
                //此时由于图片还没有下载完毕,需要让显示线程进入到阻塞状态
                //等待下载线程执行完毕之后再继续执行
                //Thread.sleep(3000); 无法精确预估下载线程何时结束
                try {
                    //join参加 插队
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getName()+":开始显示图片...");
                if (!isDownLoad){
                    throw new RuntimeException("图片显示失败");
                }
                System.out.println(getName()+":图片显示完毕！");
            }
        };
        t1.start();
        t2.start();
    }
}
