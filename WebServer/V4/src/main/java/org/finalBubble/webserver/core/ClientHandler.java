package org.finalBubble.webserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 该类是线程任务类,负责与指定的客户端进行HTTP交互
 */
public class ClientHandler implements Runnable{
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //1、解析请求
            //1.1、解析请求行
            String line = readLine();
            System.out.println(line);
            /*
             * GET /myweb/index.html HTTP/1.1
             * 请求方式(SP)抽象路径(SP)协议版本
             */
            //请求行的相关信息
            String method;//请求方式
            String uri;//抽象路径
            String protocol;//协议版本
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
            //1.2、解析消息头
            //消息头相关信息
            Map<String,String> headers = new HashMap<>();
            //消息头存在多行,所以一直读取即可
            while (true){
                line = readLine();
                /*
                 * xxxxxx: xxxx(CRLF)--readLine()-->xxxxxx: xxxx(CR)--trim()-->xxxxxx: xxxx
                 * (CRLF)--readLine()-->(CR)--trim()-->空串
                 */
                if (line.isEmpty()){//如果读取的字符串是空串,说明消息头结束了
                    //判断空串
                    //line.length == 0
                    //"".equals(line)
                    break;
                }
                System.out.println("消息头:" + line);
                //将消息头根据冒号+空格拆分为消息头的名字和消息头的值,并且以key和value的形式存储进headers中
                data = line.split(":\\s");
                headers.put(data[0], data[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
}
