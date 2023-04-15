package day20230415;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FOSDemo {
    public static void main(String[] args) throws IOException {
        //1. 创建一个FileOutStream输出流，将数据输出到"./fos.txt"
        File file = new File("JavaSE/fos.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        //2. 开始向fos.txt中写数据
        //写出一个字节，写出的是给定的int值对应的2进制低八位
        fileOutputStream.write(97);//a
        fileOutputStream.write(98);//b
        fileOutputStream.write(99);//c
        fileOutputStream.write(100);//d
        fileOutputStream.write(13);//回车
        fileOutputStream.write(10);//换行
//        System.out.println("成功写入数据到fos.txt");
        //可以直接写出一组字节数据
        //getBytes()将字符串转换为字节数据
        fileOutputStream.write("ABCDE\r\n".getBytes(StandardCharsets.UTF_8));
        //写出字节数组的一部分(off表示从哪个下标开始写，len表示写出的长度)
        fileOutputStream.write("~~~HELLO\r\n~~~".getBytes(StandardCharsets.UTF_8),3,7);
        System.out.println("成功写入数据到fos.txt");
        //只要使用了IO流，就必须用完关闭
        fileOutputStream.close();
    }
}
