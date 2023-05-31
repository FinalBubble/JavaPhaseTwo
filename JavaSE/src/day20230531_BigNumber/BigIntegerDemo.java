package day20230531_BigNumber;

import java.math.BigInteger;

public class BigIntegerDemo {
    public static void main(String[] args) {
        String value1 = "7777777777666666666655555555554444444444333333333322222222221111111111";
        String value2 = "1111111111222222222233333333334444444444555555555566666666667777777777";
        BigInteger bigInteger = new BigInteger(value1);
        BigInteger bigInteger1 = new BigInteger(value2);
        System.out.println("value1 = " + bigInteger);
        System.out.println("value2 = " + bigInteger1);
        System.out.println("value1 + value2 = " + bigInteger1.add(bigInteger));
        System.out.println("value1 - value2 = " + bigInteger1.subtract(bigInteger));
        System.out.println("value1 * value2 = " + bigInteger1.multiply(bigInteger));
        System.out.println("value1 / value2 = " + bigInteger1.divide(bigInteger));
        System.out.println("value1 % value2 = " + bigInteger1.remainder(bigInteger));
        //除并求余
        BigInteger[] bigIntegers = bigInteger.divideAndRemainder(bigInteger1);
        System.out.println(bigIntegers[0]);
        System.out.println(bigIntegers[1]);

    }
}
