package day20230414;


import java.io.File;

/**
 * 递归遍历目录以及其子项
 */
public class RecursionDirDemo {
    public static void main(String[] args) {
        File file = new File("JavaSE");
        recursionFolder(file);
    }
    public static void recursionFolder(File dir){
        //1.判断当前File是否为文件(防止第一次传入的是文件)
        //1.1.如果file是文件, 输出: "文件不支持递归!"
        if (dir.isFile()){
            System.out.println("文件不支持递归！");
            return;
        }

        //1.2.如果file是目录, 则继续执行第2步
        //2.获取当前目录下的所有子目录及子文件对象组成的File数组
        File[] files = dir.listFiles();
        //3.遍历当前目录下的所有子目录及子文件对象
        for (int i = 0; i < files.length; i++) {
//            System.out.println(files[i].getName());
            //4.判断当前遍历的是目录还是文件
            //4.1.如果当前遍历的是文件, 输出该文件的路径+名称
            //4.2.如果当前遍历的是目录, 输出当前目录的路径+名称
            if (files[i].isFile()){
                System.out.println("文件："+files[i]);
//                System.out.println(files[i].getName()+"文件/目录删除");
            }else {
                System.out.println("目录："+files[i]);
                //并以此目录作为根, 接着遍历该目录下的所有子目录和子文件, 输出该目录下的所有目录和文件名
                recursionFolder(files[i]);

            }
            System.out.println(files[i].getName()+"文件/目录删除");
//            files[i].delete();

        }


    }
    
}
