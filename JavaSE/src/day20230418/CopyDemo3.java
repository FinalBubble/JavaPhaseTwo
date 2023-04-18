package day20230418;


import java.io.*;

/**
 *使用缓冲流提供复制文件的效率
 *
 */
public class CopyDemo3 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("JavaSE/io/a.png");
        FileOutputStream fos = new FileOutputStream("JavaSE/io/e.png");
        //准备一组缓冲流
        BufferedInputStream bis = new BufferedInputStream(fis);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        int len;
        long start = System.currentTimeMillis();
        while ((len = bis.read()) != -1){//使用缓冲流读取字节
            bos.write(len);//使用缓冲流写入字节
        }
        long end = System.currentTimeMillis();
        System.out.println("复制完毕，耗时："+(end - start)+"毫秒!");
        //关闭流时，只需要关闭高级流即可，会自动将所连的流一起关闭
        bis.close();
        bos.close();
    }
}
