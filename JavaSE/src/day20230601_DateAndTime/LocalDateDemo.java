package day20230601_DateAndTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class LocalDateDemo {
    public static void main(String[] args) {
        //获取当前时间对应的LocalDate对象
        LocalDate localDate = LocalDate.now();

        //输出格式为：yyyy-MM-dd 例如:2023-06-01
        System.out.println(localDate);
        System.out.println();

        //获取指定日期的LocalDate对象
        //期望值：2023-6-1
        System.out.println("通过2023, 6, 1这3个数值来创建LocalDate对象");
        localDate = LocalDate.of(2023, 6, 1);
        System.out.println(localDate);
        System.out.println();

        //通过字符串的日期获取对应的LocalDate对象
        //参考值：2003-7-15
        System.out.println("通过\"2003-7-15\"字符串获取对应的LocalDate对象");
        localDate = LocalDate.parse("2003-07-15");
        System.out.println(localDate);
        System.out.println();

        //获取各详情值的API
        System.out.println("获取localDate("+localDate+")的详情值");
        int year = localDate.getYear();
        System.out.println("getYear() --> " + year);
        int monthValue = localDate.getMonthValue();
        System.out.println("getMonthValue() --> " + monthValue);
        int dayOfMonth = localDate.getDayOfMonth();
        System.out.println("getDayOfMonth() --> " + dayOfMonth);
        int dayOfYear = localDate.getDayOfYear();
        System.out.println("getDayOfYear() --> " + dayOfYear);
        int dayOfWeekValue= localDate.getDayOfWeek().getValue();
        System.out.println("getDayOfWeek().getValue() --> " + dayOfWeekValue);
        boolean isLeapYear = localDate.isLeapYear();
        System.out.println("isLeapYear() --> " + isLeapYear);
        int lengthOfMonth = localDate.lengthOfMonth();
        System.out.println("lengthOfMonth() --> " + lengthOfMonth);
        int lengthOfYear = localDate.lengthOfYear();
        System.out.println("lengthOfYear() --> " + lengthOfYear);
        System.out.println();


        //调用with前缀的方法修改某些字段的值
        //原对象：localDate（2003-07-15）
        //期望值：年份改为 2009
        LocalDate result;
        result = localDate.withYear(2009);
        System.out.println("将localDate("+localDate+")的【年份】改为2009："+result);
        //期望值：月份改为 5
        result = localDate.withMonth(5);
        System.out.println("将localDate("+localDate+")的【月份】改为5："+result);
        //期望值：当月的第几天 23
        result = localDate.withDayOfMonth(23);
        System.out.println("将localDate("+localDate+")的【当月的第几天】改为3："+result);
        //期望值：全年的第几天 35
        result = localDate.withDayOfYear(35);
        System.out.println("将localDate("+localDate+")的【全年的第几天】改为35："+result);
        System.out.println();

        //通过plus和minus前缀的方法增加和减少某些字段的值
        System.out.println("通过plus和minus前缀的方法增加和减少某些字段的值");
        result = localDate.plusYears(2);
        System.out.println("将localDate("+localDate+")的【年份】【增加】【2】："+result);
        result = localDate.minusYears(5);
        System.out.println("将localDate("+localDate+")的【年份】【减去】【5】："+result);
        result = localDate.plusMonths(8);
        System.out.println("将localDate("+localDate+")的【月份】【增加】【8】："+result);
        result = localDate.minusMonths(9);
        System.out.println("将localDate("+localDate+")的【月份】【减去】【9】："+result);
        System.out.println();

        //通过with()方法得到相关LocalDate对象
        System.out.println("通过with()方法得到相关LocalDate对象");
        result = localDate.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("将localDate("+localDate+")的相关【该月的第一天/firstDayOfMonth()】："+result);
        result = localDate.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("将localDate("+localDate+")的相关【该月的最后一天/lastDayOfMonth()】："+result);
        result = localDate.with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println("将localDate("+localDate+")的相关【下个月的第一天/firstDayOfNextMonth()】："+result);
        result = localDate.with(TemporalAdjusters.firstDayOfYear());
        System.out.println("将localDate("+localDate+")的相关【该年的第一天/firstDayOfYear()】："+result);
        result = localDate.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("将localDate("+localDate+")的相关【该年的最后一天/lastDayOfYear()】："+result);
        result = localDate.with(TemporalAdjusters.firstDayOfNextYear());
        System.out.println("将localDate("+localDate+")的相关【明年的第一天/firstDayOfNextYear()】："+result);
        System.out.println();

        //基于当前LocalDate对象来创建LocalDateTime对象、
        LocalDateTime localDateTime;
        localDateTime = localDate.atStartOfDay();
        System.out.println("基于localDate("+localDate+")使用" +
                "atStartOfDay()【一天的开始】得到LocalDateTime对象："+localDateTime);
        localDateTime = localDate.atTime(9,43);
        System.out.println("基于localDate("+localDate+")使用" +
                "atTime()【9,43】得到LocalDateTime对象："+localDateTime);
        localDateTime = localDate.atTime(9,43,27);
        System.out.println("基于localDate("+localDate+")使用" +
                "atTime()【9,43,27】得到LocalDateTime对象："+localDateTime);
        localDateTime = localDate.atTime(9,43,27,1234);
        System.out.println("基于localDate("+localDate+")使用" +
                "atTime()【9,43,27,1234】得到LocalDateTime对象："+localDateTime);



    }
}
