package day20230417;


import java.io.FileInputStream;
import java.io.IOException;

/*
* FIS FileInputStream 文件字节输入流，以字节为单位从磁盘中的文件读取数据
* */
public class FISDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("JavaSE/fos.txt");
        //使用read方法，每次读取目标文件中的一个字节
        /*
        * int read()
        * 读取目标文件的一个字节，并以整数形式返回
        * 如果读取不到的时，返回一个-1
        * */
        int data = fileInputStream.read();
        while (data != -1){
            System.out.println((char) data);
            data = fileInputStream.read();
        }
        fileInputStream.close();
    }
}
