package day20230601_DateAndTime;

import java.util.Date;

public class DateDemo {
    public static void main(String[] args) {
        //创建当前时间的Date对象
        Date date1 = new Date();
        //输出
        System.out.println("当前时间的Date对象："+date1);
        System.out.println("=====================================================================================");
        //创建指定时间的Date对象
        long time = 1000;//该值是即将创建的Date对象所表示的“从Java元年到此时经历的毫秒时间”
        Date date2 = new Date(time);
        System.out.println("当使用" + time + "作为构造方法参数时创建的Date对象："+date2);
        System.out.println("=====================================================================================");

        //获取Date对象所表示的时间戳
        //调用getTime()方法
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        System.out.println("以上第1个Date对象表示的时间戳：" + time1);
        System.out.println("以上第2个Date对象表示的时间戳：" + time2);
        System.out.println("=====================================================================================");

        //重新设置Date对象的时间戳
        //调用setTime(long)方法
        date1.setTime(time2);
        date2.setTime(time1);
        System.out.println("通过调用setTime(long)方法对换了2个Date对象的时间戳：");
        System.out.println("第1个Date对象：" + date1);
        System.out.println("第2个Date对象：" + date2);
        System.out.println("=====================================================================================");

        //对比2个Date对象的时间先后
        boolean result;
        //通过 boolean before(Date)方法判断当前对象是否是在参数对象之前的时间
        System.out.println("通过通过 boolean before(Date)方法判断时间先后：");
        result = date1.before(date2);
        System.out.println("date1是在date2之前的时间：" + result);
        result = date2.before(date1);
        System.out.println("date2是在date1之前的时间：" + result);
        System.out.println("=====================================================================================");

        //通过 boolean after(Date)方法判断当前对象是否是在参数对象之前的时间

        System.out.println("通过通过 boolean after(Date)方法判断时间先后：");
        result = date1.after(date2);
        System.out.println("date1是在date2之后的时间：" + result);
        result = date2.after(date1);
        System.out.println("date2是在date1之后的时间：" + result);
        System.out.println("=====================================================================================");

        //以下方法均被声明为已过期
        //获取日期、时间的详情值
        //通过 int getYear() 获取当前年
        int year = date2.getYear();
        System.out.println("通过int getYear() 获取当前年：" + year);
        int fixedYear = year + 1900;
        System.out.println("修正（加1900）后的年份：" + fixedYear);
        System.out.println("=====================================================================================");

        //通过 int getMonth() 获取当前月份
        int month = date2.getMonth();
        System.out.println("通过 int getMonth() 获取当前月份：" + month);
        int fixedMonth = month + 1;
        System.out.println("修正（加1）后的月份：" + fixedMonth);
        System.out.println("=====================================================================================");

        //通过int getDate()获取当前日（当前月份中的第几天），将返回：实际值，与日常生活中的认知相同
        int date = date2.getDate();
        System.out.println("通过int getDate()获取当前日（无需修正）:" + date);
        //通过int getDay()获取当前星期几
        int day = date2.getDay();
        System.out.println("通过int getDay()获取当前星期几（无需修正）:" + day);
        System.out.println("=====================================================================================");

        //通过int getHours()获取当前小时值
        int hours = date2.getHours();
        System.out.println("通过int getDay()获取当前小时值（无需修正）:" + hours);
        System.out.println("=====================================================================================");
        //通过int getMinutes()获取当前分钟值
        int minutes = date2.getMinutes();
        System.out.println("通过int getDay()获取当前星期几（无需修正）:" + minutes);
        System.out.println("=====================================================================================");
        //通过int getSeconds()获取当前秒钟值
        int seconds = date2.getSeconds();
        System.out.println("通过int getDay()获取当前秒钟值（无需修正）:" + seconds);
        System.out.println("=====================================================================================");

    }
}
