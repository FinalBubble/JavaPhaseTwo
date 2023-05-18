package org.finalBubble.webserver.core;

import org.finalBubble.webserver.http.HttpServletRequest;
import org.finalBubble.webserver.http.HttpServletResponse;

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
            HttpServletResponse response = new HttpServletResponse(socket);
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
            String line;
            if (file.isFile()) {
                //此处无须设置状态码和描述短语,但是需要将定位的资源发送给response作为响应正文
                response.setContentFile(file);
            } else {
                response.setStatusCode(404);
                response.setStatusReason("NotFound");
                //代码执行到此处,说明要返回给客户端一个404页面,所以需要将file定位到404页面
                file = new File(staticDir,"/root/404.html");
                response.setContentFile(file);
            }
            //3发送响应

            response.response();
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

}
