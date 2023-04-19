package day20230419;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * OSW OutputStreamWriter  衔接输出字节流和输出字符流
 * ISR InputStreamReader   衔接输入字节流和输入字符流
 * 作用：
 * 1、在流链接中用于衔接其他的高级字符流与下面的字节流
 * 2、负责将字符与对应的字节按照指定的编码表自动转换（编码和解码）
 * 这两个流一般工作中不直接操作这对流，但是他们本身的实际意义很重要
 */
public class OSWDemo {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("JavaSE/io/osw.txt");
        //创建一个高级字符流
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        osw.write("测试使用字符串");
        osw.write("测试使用字符串");
        osw.write("测试使用字符串");
        osw.close();
    }

}
