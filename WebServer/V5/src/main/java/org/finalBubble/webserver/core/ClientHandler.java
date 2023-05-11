package org.finalBubble.webserver.core;

import org.finalBubble.webserver.http.HttpServletRequest;

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
            //1解析请求
            HttpServletRequest request = new HttpServletRequest(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
