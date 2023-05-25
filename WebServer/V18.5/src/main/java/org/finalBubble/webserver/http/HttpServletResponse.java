package org.finalBubble.webserver.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.finalBubble.webserver.http.HttpContext.CR;
import static org.finalBubble.webserver.http.HttpContext.LF;

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
    //响应头的相关信息 key:响应头的名字 value:响应头的值
    private Map<String,String> headers = new HashMap<>();
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
        //遍历headers响应头容器,将所有的响应头发送给浏览器
        //entrySet() 获取headers中的每一组键值对,将其封装到一个Set集合中
        Set<Map.Entry<String, String>> entries = headers.entrySet();
        //遍历Set集合,获取里面的entry对象(一对键值对)
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();//响应头的名字 Content-Type
            String value = entry.getValue();//响应头的值 12345
            //将响应头的名字和响应头的值,按照HTTP协议要求,拼接成一个响应头
            String line = key + ":" + " " + value;
            println(line);

        }
        //响应头结束,还需要单独发一组回车+换行作为结束
        println("");
    }

    /*
     * 发送响应正文
     */
    private void sendContent() throws IOException {
        //发送响应信息给浏览器
        OutputStream outputStream = socket.getOutputStream();
        if (contentFile != null){
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
    }

    /**
     * 将字符串发送给客户端
     */
    private void println(String line) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        //由于outputStream是字节流，只能发送字节，所以line需要由String转换为byte[]
        byte[] data = line.getBytes(StandardCharsets.ISO_8859_1);
        outputStream.write(data);
        outputStream.write(CR);//发送回车符
        outputStream.write(LF);//发送换行符
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
        //1:首先根据file获取其表示的文件名(带后缀)
        String fileName = contentFile.getName();//index.html
        //2:根据获取的文件名,及去除后缀名 subString()
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        //3:根据后缀名设置Content-Type的值
        String mime = HttpContext.getMimeType(ext);
        addHeader("Content-Type", mime);
        addHeader("Content-Length", contentFile.length()+"");
    }

    /*
     * 添加一个需要发送的响应头
     */
    public void addHeader(String name, String value){
        this.headers.put(name,value);
    }
}
