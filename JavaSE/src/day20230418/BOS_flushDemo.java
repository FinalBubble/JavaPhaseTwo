package day20230418;


import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 缓存流写出数据的时候的缓冲区问题
 */
public class BOS_flushDemo {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("JavaSE/io/flush.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bos.write("super idol的笑容~~~ ".getBytes(StandardCharsets.UTF_8));
        bos.flush();//强制缓存数组，不用装满，就可以对外输出
//        bos.close();

    }

}
