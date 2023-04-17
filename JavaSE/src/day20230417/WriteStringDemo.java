package day20230417;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*
* 向文件中写出字符串
* */
public class WriteStringDemo {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("JavaSE/io/ws.txt");
        String line = "ARM SecurCore SC300：针对高性能的智能卡和嵌入式安全应，内置了 ARM Cortex-M3 微控制器内核，功率较大，封装面积较大；";
        fileOutputStream.write(line.getBytes(StandardCharsets.UTF_8));
        fileOutputStream.close();
    }
}
