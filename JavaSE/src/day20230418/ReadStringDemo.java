package day20230418;


import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 从文件中读取字符串
 *
 */
public class ReadStringDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("JavaSE/io/note2.txt");
        //available() 获取文件长度
        byte[] data = new byte[fileInputStream.available()];

        fileInputStream.read(data);
        //将字节数组转换为字符串,并指定编码
        String s = new String(data, StandardCharsets.UTF_8);
        System.out.println(s);
        fileInputStream.close();
    }
}
