package day20230419;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * PW PrintWriter 是具有自动行刷新goon功能的缓冲字符输出流
 * BufferedReader 和 BufferedWriter
 */

public class PWDemo {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        //可以通过第二个参数来指定字符集，不过需要使用字符串的格式
        PrintWriter pw = new PrintWriter("JavaSE/io/pw.txt","utf-8");
        pw.println("窗前明月光");
        pw.println("疑是地上霜");
        pw.println("举头望明月");
        pw.println("低头思故乡");
        pw.close();
    }
}
