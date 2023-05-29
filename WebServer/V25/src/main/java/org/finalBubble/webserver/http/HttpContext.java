package org.finalBubble.webserver.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

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
        //想方设法读取到web.properties文件
        /**
         * 此处使用的是一种常用的配置文件 .properties,
         * 这类文件存储的值,都是以key=value的存储的,
         * 就是可以将其内容解析之后,存储进Map中
         * 就有专门解析该类型文件的工具类:java.util.Properties
         * Properties本身就是一个Map,
         * Properties继承了HashTable,而HashTable实现了Map
         */
        Properties properties = new Properties();
        try {
            /**
             * Properties可以通过调用load方法,来读取传入的流中的内容
             * 实际开发中,常用的两种相对路径的区别:
             * 类名.class.getClassLoader.getResource(".")
             * 这里的"."指的是,当前类所在的包中的最顶级的包的上一级,
             * 也就是HttpContext所在包是com.webserver.http,
             * 该包路径的顶级包是com,而com的上一级是target/classes
             * ==============================================
             * 类名.class.getResource(".")
             * 这里的"."指得是,当前类所在的目录
             * 也就是HttpContext所在的目录是http,
             * 那么"."就是指的是http
             */
            properties.load(
                    HttpContext.class.getResourceAsStream(
                            "./web.properties"
                    )
            );
            //现在的数据都已经解析成key和value存储到prop中
            properties.forEach(
                    //Properties中存储元素时,默认是Object类型
                    (k,v) -> mimeMapping.put(k.toString(), v.toString())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //根据资源的后缀获取对应的mime类型
    public static String getMimeType(String ext) {
        return mimeMapping.get(ext);
    }

    public static void main(String[] args) {
        mimeMapping.forEach(
                (k,v)-> System.out.println(k+"==="+v)
        );
    }
}
