package day20230421;


import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 聊天室客户端
 */
public class Client {
    /**
     * java.net.Socket 套接字 原意是插座
     * Socket封装了TCP的细节，可以利用该对象实现连接远端计算机，
     * 并且基于一堆流的IO操作实现与远端计算机的数据交换
     * 形象的理解Socket就是生活中的电话
     * 电话先拨号，建立连接，然后使用话筒说话，使用听筒听对方的声音
     * Socket也需要先和服务器建立连接,然后利用输出流输出信息,使用输入流接收信息
     */
    private Socket socket;
    private static String name;
    /**
     * 初始化客户端
     */
    public Client(){
        /**
         * 实例化Socket的同时,也是在连接远端计算机
         * 如果想要连接到指定的远端计算机,必须要传入两个参数:
         * 参数1: 远端计算机的地址信息(IP)
         *  如果连接的是外界的服务器,就使用对方的IP地址,即可定位对方的服务器
         *  由于本次案例,连接的是自己电脑中的服务器,所以这个IP直接使用代称即可
         *      127.0.0.1 永远可以代表自己电脑的IP
         *      localhost 本地IP的域名
         * 参数2: 远端计算机打开的服务器端口号
         *  服务器硬件是指计算机,但是真正的服务器都需要服务器软件支持,
         *  是程序,就需要启动,只要启动,就必须要占用计算机中的端口
         *  提前预定服务器占用的端口是8088,如果该端口被别的程序占用了,
         *  自行更换一个新的端口选择范围推荐大于8000
         */
        try {
            System.out.println("正在连接服务器...");
            /*
             * 实例化ServerSocket的同时需要指定要打开的服务器端口,
             * 客户端就是通过该端口建立和服务器的连接的
             */
            socket = new Socket("localhost",8088);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 客户端开始工作的方法
     */
    public void start(){
        try {
            /*
             *  getOutputStream()
             * 该方法可以获取一个输出字节流,该输出流可以向socket连接的服务器,发送字节信息
             */
            OutputStream outputStream = socket.getOutputStream();
            //要发送字符串给服务器,所以使用PrintWriter流比较适合
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter,true);
//            printWriter.write("你好！");
            Scanner scanner = new Scanner(System.in);
            while (true){
                System.out.print(name + "说:>");
                String line = scanner.nextLine();
                if ("exit".equals(line)){
                    break;
                }
                printWriter.println(name + "说:>" + line);
            }
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            try {
                socket.close();//可以将socket的流链接关闭,并且进行TCP挥手
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("请输入您的用户名：");
        name = new Scanner(System.in).nextLine();
        Client client = new Client();
        client.start();
    }
}
