package day20230414;

import java.io.File;

/*
* 获取一个目录中的子项
* */
public class ListFilesDemo {
    public static void main(String[] args) {
        File dir = new File("JavaSE/src/day20230414");
        File[] files = dir.listFiles();
        //要遍历的对象.fori 快速生成利用下标遍历该对象的for循环
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getName());
        }
    }
}
