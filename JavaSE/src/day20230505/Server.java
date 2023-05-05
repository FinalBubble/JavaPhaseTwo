package day20230505;


import java.io.*;
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
        PrintWriter printWriter = null;
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
                printWriter = new PrintWriter(bufferedWriter, true);
                //同步监视器对象选择什么？对于多个线程而言，这个对象必须是唯一的
                /*
                 * 多个线程并发操作的临界资源就可以当做是同步监视器对象
                 * 但是在这个案例中不可以,因为allOut数组进行过扩容了
                 * 数组经过扩容,其实就是一个新的数组了,所以多个线程看到的allOut
                 * 都是不同的数组
                 */
                //内部类指向外部类的对象
                synchronized (Server.this){
                    //将该客户端的输出流存储到allOut集合中
                    //1.对allOut数组进行扩容（长度+1）
                    allOut = Arrays.copyOf(allOut,allOut.length + 1);
                    //2.讲printWriter存储到allOut数组的最后一个位置 allOut.length - 1 最大下标
                    allOut[allOut.length - 1] = printWriter;
                }
                //广播通知所有客户端该用户上线了
                sendMessage("欢迎一个客户端上线了,当前在线人数:" + allOut.length);
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    System.out.println(line);
                    sendMessage(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                synchronized (Server.this){
                    //将当前客户端的输出流从allOut数组中取出
                    //1.先获取将要删除的目标元素
                    for (int i = 0; i < allOut.length; i++) {
                        if (allOut[i] == printWriter){
                            //2.将最后一个元素替换到目标元素
                            allOut[i] = allOut[allOut.length - 1];
                            //3.将数组进行缩容
                            allOut = Arrays.copyOf(allOut, allOut.length - 1);
                            //由于数组中本身没有重复的元素,所以找到了目标元素，删除之后，就无须遍历
                            break;
                        }
                    }
                }


                //当客户端断开,服务器端也断开
                try {
                    socket.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    /*
     * 广播消息给所有客户端
     */
    private void sendMessage(String message){
        for (int i = 0; i < allOut.length; i++) {
            allOut[i].println(message);
        }
    }
}
