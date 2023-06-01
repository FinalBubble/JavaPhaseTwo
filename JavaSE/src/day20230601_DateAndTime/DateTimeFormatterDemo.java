package day20230601_DateAndTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;

public class DateTimeFormatterDemo {
    public static void main(String[] args) {
        //创建LocalDateTime对象，用于被格式为字符串
        LocalDateTime now = LocalDateTime.now();
        System.out.println("创建LocalDateTime对象，用于被格式为字符串"+ now);
        //创建/设计模式字符串，即需要将日期时间格式化为哪种格式
        String pattern = "yyyy-MM-dd HH:mm:ss";
        System.out.println("模式字符串：" + pattern);
        //创建/获取DateTimeFormatter对象，它是格式化日期时间的工具
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        //执行格式化，并获取结果
        String result = formatter.format(now);
        System.out.println("格式化完成："+ result);
        System.out.println();

        //调用LocalDateTime的format()方法进行格式化
        System.out.println("调用LocalDateTime的format()方法进行格式化");
        result = now.format(formatter);
        System.out.println("格式化完成："+ result);
        System.out.println();

        //将字符串格式的日期时间转换为对象
        String dateTimeString = "2003-07-05 16:23:51";
        System.out.println("准备字符串格式的日期时间："+dateTimeString);
        TemporalAccessor temporalAccessor = formatter.parse(dateTimeString);
        System.out.println("获取转换后的结果类型："+temporalAccessor.getClass().getName());
        System.out.println("判断转换后的结果是否归属LocalDateTime类型：" + (temporalAccessor instanceof LocalDateTime));
        System.out.println("转换后的结果："+temporalAccessor);

        //将TemporalAccessor结果（基于日期和时间的字符串成功转换后的结果）转换为LocalDateTime
        LocalDate localDate = temporalAccessor.query(TemporalQueries.localDate());
        LocalTime localTime = temporalAccessor.query(TemporalQueries.localTime());
        LocalDateTime localDateTime = localTime.now().atDate(localDate);
        System.out.println("经过复杂的转换，得到LocalDateTime对象："+ localDateTime);
        System.out.println();

        //使用LocalDateTime的parse()方法，将字符串转换为日期时间对象
        LocalDateTime localDateTime1 = LocalDateTime.parse(dateTimeString,formatter);
        System.out.println("通过LocalDateTime的parse()方法进行转换："+ localDateTime1);
        System.out.println();
    }
}
