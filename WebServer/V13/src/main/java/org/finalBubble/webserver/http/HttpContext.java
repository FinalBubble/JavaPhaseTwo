package org.finalBubble.webserver.http;

import java.util.HashMap;

public class HttpContext {
    private static HashMap<String, String> mimeMapping = new HashMap<>();
    /**
     * 回车符
     */
    public static final char CR = 13;
    /**
     * 换行符
     */
    public static final char LF = 10;

    static {
        initMimeMapping();
    }
    private static void initMimeMapping() {
        /**
         * 程序执行到此处，说明客户端请求的一定是具体的资源，但是资源是什么类型，
         * 我们就需要执行对应响应头Content-Type的mime值
         * html text/html
         * css  text/css
         * js   application/javascript
         * gif  image/gif
         * jpg  image/jpeg
         * png  image/png
         * 具体思路：
         * 0.可以将上述的六种对应关系的值，存储到一个map中
         * 1.首先根据file获取其表示的文件名（带后缀）logo.jpg
         * 2.根据获取的文件名，及去除后缀名subString() jpg
         * 3.根据后缀名设置Content-type的值
         */
        mimeMapping.put("html", "text/html");
        mimeMapping.put("css", "text/css");
        mimeMapping.put("js", "application/javascript");
        mimeMapping.put("gif", "image/gif");
        mimeMapping.put("jpg", "image/jpeg");
        mimeMapping.put("png", "image/png");
    }
    //根据资源的后缀获取对应的mime类型
    public static String getMimeType(String ext) {
        return mimeMapping.get(ext);
    }
}
