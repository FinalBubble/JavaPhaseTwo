package org.finalBubble.webserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求对象
 * 该类的每一个对象实例都用于表示HTTP协议规定的客户端发送过来的一个请求内容
 * 每个请求由三部分组成:
 * 请求行,消息头,消息正文
 */
public class HttpServletRequest {
    private Socket socket;
    /*
     * GET /myweb/index.html HTTP/1.1
     * 请求方式(SP)抽象路径(SP)协议版本
     */
    //请求行的相关信息
    private String method;//请求方式
    private String uri;//抽象路径
    private String protocol;//协议版本



    //消息头相关信息
    private Map<String,String> headers = new HashMap<>();

    /*
     * 实例化HttpServletRequest对象的同时,可以完成请求信息的解析
     */
    public HttpServletRequest(Socket socket) throws IOException{
        this.socket = socket;
        //1.1解析请求行
        parseRequestLine();
        //1.2解析消息头
        parseHeaders();
        //1.3解析消息正文
        parseContent();

        }

    /*
     * 解析请求行
     */
    private void parseRequestLine() throws IOException {
        //1.1、解析请求行
        String line = readLine();
        System.out.println(line);
        /*
         * 将请求行内容通过空格拆分并分别赋值给三个变量
         * 正则表达式中"\s"表示空格,但是"\"需要转义,所以使用"\\s"
         * [GET,/myweb/index.html,HTTP/1.1]
         */
        String[] data = line.split("\\s");
        method = data[0];
        uri = data[1];
        protocol = data[2];
        System.out.println("请求方式:" + method);
        System.out.println("抽象路径:" + uri);
        System.out.println("协议版本:" + protocol);
    }

    /*
     * 解析消息头
     */
    private void parseHeaders() throws IOException {
        //消息头存在多行,所以一直读取即可
        while (true) {
            String line = readLine();
            /*
             * xxxxxx: xxxx(CRLF)--readLine()-->xxxxxx: xxxx(CR)--trim()-->xxxxxx: xxxx
             * (CRLF)--readLine()-->(CR)--trim()-->空串
             */
            if (line.isEmpty()) {//如果读取的字符串是空串,说明消息头结束了
                //判断空串
                //line.length == 0
                //"".equals(line)
                break;
            }
            System.out.println("消息头:" + line);
            //将消息头根据冒号+空格拆分为消息头的名字和消息头的值,并且以key和value的形式存储进headers中
            String[] data = line.split(":\\s");
            headers.put(data[0], data[1]);
        }
    }
    /*
     * 解析消息正文
     */
    private void parseContent() {

    }
    //用于封装读取一行字符串的功能
    private String readLine() throws IOException{
        InputStream inputStream = socket.getInputStream();
        int d;
        StringBuilder stringBuilder = new StringBuilder();
        char pre = 'a';//上一次读取的字符
        char cur = 'a';//本次读取的字符
        //每次读取一个字节,也就是一个字符
        while ((d = inputStream.read()) != -1){
            //将本次读取的字符d赋值给cur
            cur = (char) d;
            //判断上一次读取的字符是否是回车符并且本次读取的是否是换行符
            if ( pre == 13 && cur == 10){
                //如果判断成立,说明连续读取到了回车和换行,就直接停止读取
                break;
            }
            //说明连续读到的不是回车和换行,就将本次读取的字符拼接到builder中
            stringBuilder.append(cur);
            //将本次读取的字符赋值给上一次读取的字符,以便cur空出来,接收下一次读取的字符
            pre = cur;
            //                System.out.print( (char) d);
        }
        //由于builder中会多拼一个CR,而CR在输出时,是一个空白符,所以直接去空格即可
        String line = stringBuilder.toString().trim();
        return line;
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getProtocol() {
        return protocol;
    }

    /**
     * 根据给定的消息头的名字，返回对应的值
     * @return
     */
    public String getHeaders(String name) {
        return headers.get(name);
    }
}
