package day20230414;

import java.io.File;

public class FileDemo {
    public static void main(String[] args) {
        //访问（运行程序才能访问）当前项目目录下的demo.txt文件（在磁盘中）
        /**
         * 绝对路径 D:\MyProject\Java\Phase one\Phase two\JavaSE\demo.txt
         * 相对路径 "./"表示当前目录
         * 在idea中，"./"指的是当前程序所在的项目的目录
         */
//        File file = new File("D:\\MyProject\\Java\\JavaDevelope\\Phase two\\JavaSE\\demo.txt");
        File file = new File("JavaSE/demo.txt");
        //length(): 返回文件的长度, 单位是字节数(如果File是目录, 则返回0)
        long length = file.length();
        System.out.println("文件的长度："+length);
        //exists(): 判断当前文件或目录是否存在，存在则返回true
        boolean exists = file.exists();
        System.out.println("文件是否存在："+exists);
        //isFile(): 判断当前file是否为文件，是文件则返回true
        boolean file1 = file.isFile();
        System.out.println("是否是文件："+file1);
        //isDirectory(): 判断当前file是否为目录，是目录返回true
        boolean directory = file.isDirectory();
        System.out.println("是否是目录："+directory);
        //getName(): 获取当前File文件或目录的名字
        String name = file.getName();
        System.out.println("文件名称："+name);
        //getParent(): 获取当前File父目录的路径
        String parent = file.getParent();
        System.out.println("父目录的路径："+parent);
        //getAbsolutePath(): 获取当前File文件或目录的完整路径
        File absoluteFile = file.getAbsoluteFile();
        System.out.println("文件的完整目录："+absoluteFile);
    }
}
