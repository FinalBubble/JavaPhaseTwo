package org.finalBubble.webserver.core;

import org.finalBubble.webserver.http.HttpServletRequest;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 该类是线程任务类,负责与指定的客户端进行HTTP交互
 *
 */
public class ClientHandler implements Runnable{
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //1解析请求
            HttpServletRequest request = new HttpServletRequest(socket);
            String host = request.getHeaders("host");
            //2处理请求
            /*
             * http://localhost:8088/myweb/index.html
             * 这个URL中的抽象路径uri:/myweb/index.html
             * 而我们要利用这个路径定位当前项目中static目录下的内容
             * static/myweb/index.html
             * localhost:8088/
             */
            String path = request.getUri();
            System.out.println("抽象路径:" + path);
            //3发送响应
//            new File("WebServer/V6/src/main/resources/static/myweb/index.html");
            File file = new File(
                    /*
                     * ClientHandler.class.getClassLoader().getResource()定位文件时,
                     * "./"表示的是ClientHandler的字节码文件所在顶级包路径的上一级目录,
                     * 也就是ClientHandler的包路径是com.webserver.core,顶级包路径是com,
                     * 那么com的上一级就是target/classes目录,所以"./"表示的就是target/classes目录,
                     * 所以./static/myweb/index.html表示的就是target/classes/static/myweb/index.html
                     */
                    ClientHandler.class.getClassLoader().getResource(
                            "./static"+path
                    ).toURI()
            );
            //发送响应信息给浏览器
            OutputStream outputStream = socket.getOutputStream();
            //3.1发送状态行
            String line = "HTTP/1,1 200 OK";
            //由于outputStream是字节流，只能发送字节，所以line需要由String转换为byte[]
            println(line);
            //3.2发送响应头
            line = "Content-Type: text/html";
            println(line);
            line = "Content-Length: "+ file.length();
            println(line);
            //响应头结束,还需要单独发一组回车+换行作为结束
            println("");
            //3.3发送响应正文
            //发送响应信息给浏览器
            byte[] buf = new byte[10 * 1024];//定义缓冲区
            int len;//记录本次读取的字节量
            FileInputStream fileInputStream = new FileInputStream(file);
            while ((len = fileInputStream.read(buf)) != -1){
                outputStream.write(buf,0,len);//读取到多少字节,就写出多少字节的内容
            }
            System.out.println("响应信息发送完毕!!!!");
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        } finally {
            //HTTP协议要求交互后，立即断开连接
            try {
                socket.close();//可以断开连接的同时,也释放流资源
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    /**
     * 将字符串发送给客户端
     */
    private void println(String line) throws IOException{
        OutputStream outputStream = socket.getOutputStream();
        //由于outputStream是字节流，只能发送字节，所以line需要由String转换为byte[]
        byte[] data = line.getBytes(StandardCharsets.ISO_8859_1);
        outputStream.write(data);
        outputStream.write(13);//发送回车符
        outputStream.write(10);//发送换行符
    }
}
