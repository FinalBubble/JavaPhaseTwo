package day20230418;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 进行对象的反序列化
 */
public class OISDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("JavaSE/io/Person2.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Person p = (Person) ois.readObject();
        System.out.println(p);
        ois.close();
    }
}
