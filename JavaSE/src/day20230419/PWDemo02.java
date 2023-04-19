package day20230419;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 由于后期我们连接的对象可能不是文件了，所以PrintWriter的内部链接不能使用
 * 所需要我们自己进行流链接
 */
public class PWDemo02 {
    public static void main(String[] args) throws FileNotFoundException {
        //文件字节输出流（是一个低级流），向文件中写入字节数据，并开启追加模式
        FileOutputStream fos = new FileOutputStream("JavaSE/io/pw2.txt",true);
        //转换输出流，并指定编码
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        //缓存输出流
        BufferedWriter bw = new BufferedWriter(osw);
        //创建按行输出字符串,并开启自动行刷新
        PrintWriter pw = new PrintWriter(bw,true);
        //完成简易的记事本
        Scanner scanner = new Scanner(System.in);
        while (true){
            String line = scanner.nextLine();
            if ("exit".equals(line)){
                break;
            }
            //当开启自动行刷新功能时，每次调用println时，会自动调用一次flush
            pw.println(line);
//            pw.flush();
        }
        pw.close();

    }

}
