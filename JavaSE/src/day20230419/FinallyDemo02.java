package day20230419;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 异常处理机制在IO操作中的应用
 */
public class FinallyDemo02 {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("JavaSE/io/fos.txt");
            int i = 1/0;
            fos.write(1);
        } catch (IOException e) {
            System.out.println("出错了，得到了解决");
        } finally {
            System.out.println("即释放流资源");
            try {
                if (fos != null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
