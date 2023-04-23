package day20230421;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 聊天室的服务器端
 */
public class Server {
    /**
     * ServerSocket是运行在服务器端的,它的主要工作:
     * 1:打开服务器端口(客户端就是通过这个端口与服务器端简历连接!!)
     * 2:监听该端口,一旦一个客户端来访问服务器,就会返回一个Socket实例,
     * 并通过这个Socket实例与连接的客户端进行交互
     */
    private ServerSocket serverSocket;
    public Server(){

        try {
            System.out.println("正在启动服务端...");
            serverSocket = new ServerSocket(8088);
            System.out.println("8088服务器端启动完毕!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void start(){
        /*
         * Socket accept()
         * 一旦调用该方法,程序就会停止到这句代码,不会继续向下执行(阻塞方法),
         * 当一个客户端访问时,accept方法会监听到,就可以继续向下执行了,
         * 并且该方法,监听到客户端来访时,会返回一个Socket实例,
         * 我们可以利用该Socket与来访客户端进行交互
         * 接收
         */
        try {
            while (true) {
                System.out.println("等待客户端连接...");
                Socket socket = serverSocket.accept();
                System.out.println("一个客户端链接了!!!");
                //启动一个线程,让该线程专门和指定的客户端进行交互
                ClientHandler handler = new ClientHandler(socket);
                Thread t = new Thread(handler);
                t.start();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    /*
     * 定义线程任务类,负责与给定的客户端进行交互
     */
    private class ClientHandler implements Runnable{
        private Socket socket;
        public ClientHandler(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run() {
            try {
                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
