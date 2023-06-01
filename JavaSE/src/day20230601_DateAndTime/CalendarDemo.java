package day20230601_DateAndTime;

import java.util.Calendar;

public class CalendarDemo {
    public static void main(String[] args) {
        //通过Calendar的静态方法获取Calendar实例（对象）
        Calendar calendar = Calendar.getInstance();
        //输出
        System.out.println(calendar);

        //修改字段值
        //期望值：2020-6-22
        //注意：Calendar处理月份时，使用的是0~11，所以，6月的设置值应该是( 6 - 1 )，即：5
        System.out.println("将日期设置为：2020-6-22");
        calendar.set(2020,5,20);
        System.out.println(calendar);

        //修改字段值
        //期望值：年份 ---> 2009
        System.out.println("将年份设置为：2009");
        calendar.set(Calendar.YEAR,2009);
        System.out.println(calendar);

        //修改字段值
        //期望值：小时 ---> 21
        System.out.println("将小时设置为：21");
        calendar.set(Calendar.HOUR_OF_DAY,21);
        System.out.println(calendar);

        //获取值
        int year = calendar.get(Calendar.YEAR);
        System.out.println("当前Calendar对象表示的【年份】：" + year);
        int month = calendar.get(Calendar.MONTH);
        System.out.println("当前Calendar对象表示的【月份】：" + month);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("当前Calendar对象表示的【当月的第几天】：" + dayOfMonth);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println("当前Calendar对象表示的【当天的第几小时】：" + hourOfDay);
        int minute = calendar.get(Calendar.MINUTE);
        System.out.println("当前Calendar对象表示的【分钟】：" + minute);
        int second = calendar.get(Calendar.SECOND);
        System.out.println("当前Calendar对象表示的【秒钟】：" + second);
        System.out.println();

        //增加某字段的值
        //期望目标： 天 + 1
        System.out.println("在原日期基础上增加1天");
        calendar.add(Calendar.DAY_OF_MONTH,1);
        System.out.println(calendar);
        System.out.println();

        //增加天数后，Calendar会在必要的时候更新月、年
        //期望目标：在2015-12-30基础上增加5天，目标将是2016-1-4
        calendar.set(2015,11,30);
        calendar.add(Calendar.DAY_OF_MONTH,5);
        System.out.println("本次已将日期先调整为2015-12-30，并增加5天");
        System.out.println(calendar);
        System.out.println();

        //调用add()方法时，第二个参数amount值为负数
        calendar.add(Calendar.DAY_OF_MONTH,-6);
        System.out.println("将DAY_OF_MONTH的增量设置为-6");
        System.out.println(calendar);
        System.out.println();

        //对比2个Calendar对象的时间先后
        //第1个Calendar的时间为：2000-5-6 12:34:56
        //第2个Calendar的时间为：2021-8-16 11:33:22
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2000,5,6,12,34,56);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2021,8,16 ,11,33,22);
        System.out.println("calendar1的时间：2000-5-6 12:34:56");
        System.out.println("calendar2的时间：2021-8-16 11:33:22");
        boolean result;
        result = calendar1.before(calendar2);
        System.out.println("判断calendar1在calendar2之前：" + result);
        result = calendar2.before(calendar1);
        System.out.println("判断calendar2在calendar1之前：" + result);
        result = calendar1.after(calendar2);
        System.out.println("判断calendar1在calendar2之后：" + result);
        result = calendar2.after(calendar1);
        System.out.println("判断calendar2在calendar1之后：" + result);
        System.out.println();

        //使用其他参数来调用before/after方法
        result = calendar1.before(null);
        System.out.println("当calendar1与null对比时间先后（在其之前）时："+ result);
        result = calendar1.before("123");
        System.out.println("当calendar1与字符串对比时间先后（在其之前）时："+ result);
        result = calendar1.before(new Object());
        System.out.println("当calendar1与Object类的对象对比时间先后（在其之前）时："+ result);
        System.out.println();
        result = calendar1.after(null);
        System.out.println("当calendar1与null对比时间先后（在其之后）时："+ result);
        result = calendar1.after("123");
        System.out.println("当calendar1与字符串对比时间先后（在其之后）时："+ result);
        result = calendar1.after(new Object());
        System.out.println("当calendar1与Object类的对象对比时间先后（在其之后）时："+ result);
    }
}
