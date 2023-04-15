package day20230415;

import java.io.File;
import java.io.FileFilter;


/*
*
* 不是所有的匿名内部类都可以使用lambda表达式，必须要求实现的接口只能由一个抽象方法
*
* */
public class LambdaDemo {
    public static void main(String[] args) {
        File dir = new File("JavaSE");//获取当前项目的目录
        FileFilter fileFilter = pathname -> pathname.getName().contains("o");
        File[] subs = dir.listFiles(fileFilter);
        System.out.println("共有子项"+subs.length+"个");

        for (int i = 0; i < subs.length; i++) {
            System.out.println(subs[i].getName());
        }

        //1. 忽略接口名和方法名
        //可以通过等号左边知晓匿名内部类创建的类型，也自然会自动实例化该接口的子类对象
        //因为接口中只有一个抽象方法，所以重写只能是这个方法
        //将重写的方法的参数需要移动到匿名内部类的()中
        //将重写的方法体删除，保留方法内部的代码
        //添加->符号
        //2. 参数类型可以忽略，但顺序不能改变
        //3. 如果方法只有一个参数时，那么"()"也可以省略
        //4. 如果方法体只有一句代码时，那么可将方法体的"{}"也可以忽略
        // 此时如果代码中含有return，也要一同忽略
    }

}
