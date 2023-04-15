package day20230415;

import java.io.File;
import java.io.FileFilter;

public class ListFilesDemo02 {
    public static void main(String[] args) {
        File dir = new File("JavaSE");//获取当前项目的目录
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                String name = pathname.getName();
                System.out.println("正过滤元素："+name);
                boolean o = name.contains("o");
                return o;
            }
        };
        File[] subs = dir.listFiles(fileFilter);
        System.out.println("共有子项"+subs.length+"个");

        for (int i = 0; i < subs.length; i++) {
            System.out.println(subs[i].getName());
        }
    }
}