package day20230417;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 实现文件复制
 * 将当前项目下的io目录中的a.png图片复制一份，名字为b.png
 */
public class CopyDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("JavaSE/io/a.png");
        FileOutputStream fos = new FileOutputStream("JavaSE/io/b.png");
        int data;
        long start = System.currentTimeMillis();
        while ((data = fis.read()) != -1){
            fos.write(data);
        }
        long end = System.currentTimeMillis();
        System.out.println("复制完毕，耗时："+(end - start)+"毫秒!");
        fis.close();
        fos.close();
    }
}
