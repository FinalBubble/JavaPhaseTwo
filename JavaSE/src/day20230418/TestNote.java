package day20230418;



import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 实现简易记事本工具
 * 程序启动后，要求将控制台输入的每一行字符串都写入到当前项目的io目录下的note.txt文件中
 * 当在控制台输入exit时，退出
 */
public class TestNote {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请开始输入内容，单独输入exit时，退出");
        FileOutputStream fileOutputStream = new FileOutputStream("JavaSE/io/note.txt");
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
