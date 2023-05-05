package day20230504;


import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 聊天室的服务器端
 */
public class Server {
    //该集合用于存储所有的客户端输出流,用于广播消息给所有的客户端
    private PrintWriter[] allOut = {};

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
                //通过socket获取输出流，用于给客户端发送消息
                OutputStream outputStream = socket.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                PrintWriter printWriter = new PrintWriter(bufferedWriter, true);
                //将该客户端的输出流存储到allOut集合中
                //1.对allOut数组进行扩容（长度+1）
                allOut = Arrays.copyOf(allOut,allOut.length + 1);
                //2.讲printWriter存储到allOut数组的最后一个位置 allOut.length - 1 最大下标
                allOut[allOut.length - 1] = printWriter;
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    System.out.println(line);
                    for (int i = 0; i < allOut.length; i++) {
                        allOut[i].println(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
