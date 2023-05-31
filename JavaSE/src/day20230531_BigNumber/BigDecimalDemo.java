package day20230531_BigNumber;

import java.math.BigDecimal;

public class BigDecimalDemo {
    public static void main(String[] args) {
        //创建2个基本类型的浮点数
        double d1 = 0.1;
        double d2 = 0.2;
        //计算2个double的和
        System.out.println("两个double求和的结果 = "+ (d1 + d2));

        //创建BigDecimal对象
        String s1 = "0.1";
        String s2 = "0.2";
        //当原数使用String表示时，使用构造方法创建BigDecimal
        //当原数使用double表示时，使用静态方法valueOf()创建BigDecimal
        //当原数使用double表示时，不要使用double作为构造方法参数来创建BigDecimal对象
        BigDecimal number1 = BigDecimal.valueOf(d1); //new BigDecimal(s1);
        BigDecimal number2 = BigDecimal.valueOf(d2); //new BigDecimal(s2);
        //通过BigDecimal求和
        System.out.println("两个BigDecimal求和 = " + number1.add(number2));

    }
}
