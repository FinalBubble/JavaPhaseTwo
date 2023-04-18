package day20230418;



import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 文件输出流的追加模式
 * 默认每次执行程序，都会将note.txt中的内容覆盖掉
 */
public class TestNote02 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请开始输入内容，单独输入exit时，退出");
        FileOutputStream fileOutputStream = new FileOutputStream("JavaSE/io/note2.txt",true);
        while (true){
            String line = scanner.nextLine();
            if ("exit".equals(line)){
                break;
            }
            fileOutputStream.write(line.getBytes(StandardCharsets.UTF_8));
        }
        fileOutputStream.close();
    }
}
