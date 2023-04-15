package day20230414;


import java.io.File;
import java.io.IOException;

/*
* 使用File新建一个文件
* */
public class CreateFileDemo {
    public static void main(String[] args) throws IOException {
        File file = new File("JavaSE/test.txt");
        if (file.exists()){
            System.out.println("文件已经存在！");
        }else {
            file.createNewFile();//创建指定的文件
            System.out.println("文件已创建！");
        }

    }
}
