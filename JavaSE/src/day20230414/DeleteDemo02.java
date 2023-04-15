package day20230414;

import java.io.File;

/*
* 删除一个目录
* */
public class DeleteDemo02 {
    public static void main(String[] args) {
        File dir = new File("JavaSE/dir");
        if (!dir.exists()){
            System.out.println("目录不存在！");
        }else {
            //删除目录时，要求该目录必须是一个空目录才可以删除
            dir.delete();//删除文件，删除的文件不会放在回收站，是真的删除了
            System.out.println("目录已删除！");
        }
    }
}
