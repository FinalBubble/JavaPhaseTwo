package org.finalBubble.webserver.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 响应对象
 * 该类的每一个实例都表示一个HTTP协议规定的响应内容
 * 每个响应都是由三部分组成的
 * 状态行,响应头,响应正文
 */
public class HttpServletResponse {
    private Socket socket;
    //状态行相关信息
    private int statusCode = 200;//状态代码
    private String statusReason = "OK";//状态描述
    //响应正文的相关信息
    private File contentFile;

    public HttpServletResponse(Socket socket){
        this.socket = socket;
    }

    /*
     * 发送响应
     * 将当前的响应对象内容按照HTTP协议的要求组织成对应的响应信息的格式发送给客户端
     */
    public void response() throws IOException {
        //3.1发送状态行
        sendStatusLine();
        //3.2发送响应头
        sendHeaders();
        //3.3发送响应正文
        sendContent();
    }

    /*
     * 发送状态行
     */
    private void sendStatusLine() throws IOException {
        String line = "HTTP/1.1"+ " " +statusCode + " " + statusReason;
        println(line);
    }

    /*
     * 发送响应头
     */
    private void sendHeaders() throws IOException {
        String line = "Content-Type: text/html";
        println(line);
        line = "Content-Length: "+ contentFile.length();
        println(line);
        //响应头结束,还需要单独发一组回车+换行作为结束
        println("");
    }

    /*
     * 发送响应正文
     */
    private void sendContent() throws IOException {
        //发送响应信息给浏览器
        OutputStream outputStream = socket.getOutputStream();
        byte[] buf = new byte[10 * 1024];//定义缓冲区
        int len;//记录本次读取的字节量
        try (
                FileInputStream fileInputStream = new FileInputStream(contentFile)
        ){
            while ((len = fileInputStream.read(buf)) != -1){
                outputStream.write(buf,0,len);//读取到多少字节,就写出多少字节的内容
            }
        }
    }

    /**
     * 将字符串发送给客户端
     */
    private void println(String line) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        //由于outputStream是字节流，只能发送字节，所以line需要由String转换为byte[]
        byte[] data = line.getBytes(StandardCharsets.ISO_8859_1);
        outputStream.write(data);
        outputStream.write(13);//发送回车符
        outputStream.write(10);//发送换行符
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public File getContentFile() {
        return contentFile;
    }

    public void setContentFile(File contentFile) {
        this.contentFile = contentFile;
    }
}
