package org.finalBubble.webserver.core;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * WebServer主类
 * WebServer是一个Web容器,模拟Tomcat的基本功能
 * Web容器有两个主要功能:
 * 1:管理部署在容器中的所有的网络应用(WebApp),每个网络应用就是我们俗称的网站.
 * 它通常包含页面,处理业务的代码以及其他资源等等.
 * 2:负责与客户端(通常是浏览器)进行TCP链接,并基于HTTP协议进行交互,使得客户端可以通过网络远程调用
 * 容器中的某个网络应用
 */

public class WebServerApplication {
    private ServerSocket serverSocket;

    public WebServerApplication() {
        try {
            System.out.println("正在启动服务器~~~");
            serverSocket = new ServerSocket(8088);
            System.out.println("服务器启动完毕!!!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start(){
        try {
            System.out.println("等待客户端链接...");
            Socket socket = serverSocket.accept();
            System.out.println("一个客户端链接!!!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        WebServerApplication webServerApplication = new WebServerApplication();
        webServerApplication.start();
    }
}
