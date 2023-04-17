package day20230417;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 提高每次读写的数据量，减少读写的次数，可以提高效率
 * 一组字节一组字节的读写称之为：块读写形式
 *
 */
public class CopyDemo02 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("JavaSE/io/a.png");
        FileOutputStream fos = new FileOutputStream("JavaSE/io/d.png");
        /*
        * 有利用块读取字节的方法
        * int read(byte[] data) 一次性读取给定的字节数组的总长度的字节量
        * 把读取的内容存入到该数组中
        * 返回值为实际读取到的字节量
        * 如果返回值为-1，表示读取到了末尾
        * */
        byte[] data = new byte[1024 * 10];//10kb
        int len;//记录本次读取的字节数
        long start = System.currentTimeMillis();
        while ((len = fis.read(data)) != -1){
            fos.write(data,0,len);
        }
        long end = System.currentTimeMillis();
        System.out.println("复制完毕，耗时："+(end - start)+"毫秒!");
        fis.close();
        fos.close();
    }
}
