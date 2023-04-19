package day20230419;


import java.io.FileOutputStream;
import java.io.IOException;

/**
 * JDK7之后,java推出了自动关闭特性.
 * 使得我们在源码中异常处理机制在IO应用中得到了简化
 * 只是被编译器认可的,并不被JVM虚拟机认可
 */
public class FinallyDemo03 {
    public static void main(String[] args) {
        try (
                //在该括号中写流的声明语句,会使用完该流后,自动释放
                FileOutputStream fos = new FileOutputStream("JavaSE/io/fos.txt")
        ) {
            fos.write(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
