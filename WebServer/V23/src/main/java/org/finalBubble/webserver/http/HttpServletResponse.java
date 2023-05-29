package org.finalBubble.webserver.http;

import java.io.*;
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
    private Map<String, String> headers = new HashMap<>();
    //响应正文的相关信息
    private File contentFile;
    /**
     * 使用字节数组输出流中的字节数组,作为正文的内用
     * java.io.ByteArrayOutputStream是一个低级流,其内部维护了一个字节数组.
     * 通过这个流写出的数据全部存入到该数组中
     */
    private ByteArrayOutputStream byteArrayOutputStream;

    private byte[] contentData;//保存动态数据的(数据是从byteArrayOutputStream里获取的)

    public HttpServletResponse(Socket socket) {
        this.socket = socket;
    }

    /*
     * 发送响应
     * 将当前的响应对象内容按照HTTP协议的要求组织成对应的响应信息的格式发送给客户端
     */
    public void response() throws IOException {
        //发送前的准备工作
        sendBefore();
        //3.1发送状态行
        sendStatusLine();
        //3.2发送响应头
        sendHeaders();
        //3.3发送响应正文
        sendContent();
    }
    /*
     * 发送响应前的准备工作
     * 如果本次的响应是要发送动态页面,就设置一个响应头Content-Length
     * 如果而本次的响应不是发送动态页面,就无需设置了
     */
    private void sendBefore() {
        //只有生成了动态页面数据,才会创建byteArrayOutputStream实例,
        //所以可以利用这个特点,判断是否需要提前准备响应头
        if (byteArrayOutputStream != null){
            contentData = byteArrayOutputStream.toByteArray();
            addHeader("Content-Length", contentData.length + "");
        }
    }

    /*
     * 发送状态行
     */
    private void sendStatusLine() throws IOException {
        String line = "HTTP/1.1" + " " + statusCode + " " + statusReason;
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
        if (contentData != null){//contentData不为空,说明有动态数据
            outputStream.write(contentData);
        }
        if (contentFile != null) {//contentFile不为空,说明有静态文件,就读取静态文件发送
            byte[] buf = new byte[10 * 1024];//定义缓冲区
            int len;//记录本次读取的字节量
            try (
                    FileInputStream fileInputStream = new FileInputStream(contentFile)
            ) {
                while ((len = fileInputStream.read(buf)) != -1) {
                    outputStream.write(buf, 0, len);//读取到多少字节,就写出多少字节的内容
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
        addHeader("Content-Length", contentFile.length() + "");
    }

    /*
     * 添加一个需要发送的响应头
     */
    public void addHeader(String name, String value) {
        this.headers.put(name, value);
    }

    /**
     * 返回一个字节数组输出流,通过该输出流写出的字节,都会作为正文发送给浏览器
     */
    public ByteArrayOutputStream getByteArrayOutputStream() {
        if (byteArrayOutputStream == null) {
            byteArrayOutputStream = new ByteArrayOutputStream();
        }
        return byteArrayOutputStream;
    }

    public PrintWriter getWriter() {
        return new PrintWriter(//按行刷新字符流
                new BufferedWriter(//缓冲流
                        new OutputStreamWriter(//转换流
                                getByteArrayOutputStream(),//byteArrayOutputStream
                                StandardCharsets.UTF_8//指定编码
                        )
                ), true);
    }
    public void setContentType(String mime){
        addHeader("Content-Type",mime);
    }
}
