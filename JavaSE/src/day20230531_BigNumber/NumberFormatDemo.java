package day20230531_BigNumber;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatDemo {
    public static void main(String[] args) {
        //将被格式化的数
        double value = 93.27;
        BigDecimal number = BigDecimal.valueOf(value);

        //获取NumberFormat工具的对象
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        NumberFormat currencyInstance1 = NumberFormat.getCurrencyInstance(Locale.FRANCE);

        //执行格式化
        String percent = percentInstance.format(number);
        String currency = currencyInstance.format(number);
        String currency1 = currencyInstance1.format(number);
        //输出结果
        System.out.println(percent);
        System.out.println(currency);
        System.out.println(currency1);

    }
}
