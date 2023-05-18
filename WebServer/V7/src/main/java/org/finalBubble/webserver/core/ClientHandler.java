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
            //专门定位static目录
            File staticDir = new File(ClientHandler.class.getClassLoader().getResource(
                    "./static").toURI());
            /*
             * File(File parent,String sub)
             * 该方法可以通过parent父级目录,定位sub子级资源
             * 并且如果子级资源不存在,不会报空指针
             * 当前file实例,就可以表示用户通过URL想要获取的资源
             * 此处有两种情况:
             * 1:用户想要获取的资源真实存在 localhost:8088/myweb/index.html
             *   将存在的资源直接返回给客户端
             *   "HTTP/1.1 200 OK"
             * 2:用户想要获取的资源不存在
             *  2.1:该资源文件不存在 localhost:8088/myweb/a.html
             *  2.2:用户输入的是一个目录 localhost:8088/myweb
             *   此时,用户访问不到想要获取的资源,因为服务器中没有可以返回的资源,
             *   但是为了客户的良好体验,应该统一返回一个root/404.html页面
             *   此处注意:如果客户端访问的资源不存在,服务器需要返回的响应信息的状态行,
             *   需要发生变化 "HTTP/1.1 404 NotFound"
             */

            File file = new File(staticDir, path);
            //发送响应信息给浏览器
            OutputStream outputStream = socket.getOutputStream();
            String line;
            if (file.isFile()) {
                //3.1发送状态行
                line = "HTTP/1,1 200 OK";
            } else {
                //不是文件或者是一个目录
                line = "HTTP/1.1 404 NotFound";
                //代码执行到此处,说明要返回给客户端一个404页面,所以需要将file定位到404页面
                file = new File(staticDir,"/root/404.html");
            }

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
