package day20230601_DateAndTime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatDemo {
    public static void main(String[] args) throws Exception {
        //准备Date对象，用于格式化
        Date now = new Date();
        //创建模式字符串
        String pattern = "yyyy-MM-dd HH:mm:ss";
        System.out.println("当前模式字符串：" + pattern);
        //创建SimpleDateFormat对象
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        //调用SimpleDateFormat的format()方法执行格式化，并获取结果
        String result = sdf.format(now);
        //输出结果
        System.out.println("当前时间："+now);
        System.out.println("格式化后："+result);
        System.out.println();

        //应用新的模式字符串
        pattern = "HH:mm:ss";
        System.out.println("应用新的模式字符串:" + pattern);
        sdf.applyPattern(pattern);
        result = sdf.format(now);
        System.out.println("格式化后："+result);
        System.out.println();


        //将字符串格式的日期时间转换为Date对象
        String dateString = "2018-06-22 13:52:46";
        pattern = "yyyy-MM-dd HH:mm:ss";
        sdf.applyPattern(pattern);
        Date date = sdf.parse(dateString);
        System.out.println("将【"+dateString+"】转换为Date对象：" + date);
    }
}
