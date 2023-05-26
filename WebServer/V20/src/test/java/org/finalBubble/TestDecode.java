package org.finalBubble;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class TestDecode {
    public static void main(String[] args) {
//        String line = "username=E5AE89&password=12345";
        String line = "username=%E5%AE%89&password=12345";
        String decode = URLDecoder.decode(line, StandardCharsets.UTF_8);
        //URLDecoder.decode() 可以将出传入的字符串按照指定的字符集，将包含的16进制，还原为原字符
        System.out.println(decode);
    }
}
