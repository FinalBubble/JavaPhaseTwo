package org.finalBubble.webserver.core;

import org.finalBubble.webserver.http.EmptyRequestException;
import org.finalBubble.webserver.http.HttpServletRequest;
import org.finalBubble.webserver.http.HttpServletResponse;

import java.io.*;
import java.net.Socket;

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
            DispatcherServlet dispatcherServlet = new DispatcherServlet();
            dispatcherServlet.service(request,response);
            //3发送响应
            response.response();
            System.out.println("响应信息发送完毕!!!!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EmptyRequestException e) {
            //此处什么也不写,相当于忽视该异常
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
