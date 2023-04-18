package day20230418;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 对象流
 * OOS ObjectOutputStream 对象字节输出流
 * 将对象存储到文件中的过程也称之为序列化
 */
public class OOSDemo {
    public static void main(String[] args) throws IOException {
        String name = "张三";
        int age = 23;
        String gender = "男";
        String[] otherInfo = {"是一个活泼的","来自罗翔视频里的","法外狂徒"};
        Person p = new Person(name,age,gender,otherInfo);
        System.out.println(p);
        //将对象p写入到文件Person.txt中
        FileOutputStream fos = new FileOutputStream("JavaSE/io/Person2.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(p);
        System.out.println("写出完毕");
        oos.close();
    }
}
