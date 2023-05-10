package org.finalBubble.webserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
