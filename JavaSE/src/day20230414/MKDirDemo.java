package day20230414;

import java.io.File;

/*
* 创建一个目录
* */
public class MKDirDemo {
    public static void main(String[] args) {
        //在当前目录下创建一个新的目录：dir，目录是没有后缀的
        File dir = new File("JavaSE/dir");
        if (dir.exists()){
            System.out.println("目录已存在！");
        }else{
            dir.mkdir();
            System.out.println("目录创建成功！");
        }
    }
}
