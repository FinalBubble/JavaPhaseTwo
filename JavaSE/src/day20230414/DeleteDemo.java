package day20230414;

import java.io.File;

/*
* 删除一个文件
* */
public class DeleteDemo {
    public static void main(String[] args) {
        File file = new File("JavaSE/test.txt");
        if (!file.exists()){
            System.out.println("文件不存在！");
        }else {
            file.delete();//删除文件，删除的文件不会放在回收站，是真的删除了
            System.out.println("文件已删除！");
        }
    }
}
