package org.finalBubble.webserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static org.finalBubble.webserver.http.HttpContext.CR;
import static org.finalBubble.webserver.http.HttpContext.LF;

/**
 * 请求对象
 * 该类的每一个对象实例都用于表示HTTP协议规定的客户端发送过来的一个请求内容
 * 每个请求由三部分组成:
 * 请求行,消息头,消息正文
 */
public class HttpServletRequest {
    private Socket socket;
    /*
     * GET /myweb/index.html HTTP/1.1
     * 请求方式(SP)抽象路径(SP)协议版本
     */
    //请求行的相关信息
    private String method;//请求方式
    private String uri;//抽象路径
    private String protocol;//协议版本
    private String requestURI;//封装uri中"?"左侧内容
    private String queryString;//封装uri中"?"右侧内容
    private Map<String, String> parameters = new HashMap<>();//存储用户提交的参数


    //消息头相关信息
    private Map<String,String> headers = new HashMap<>();

    /*
     * 实例化HttpServletRequest对象的同时,可以完成请求信息的解析
     */
    public HttpServletRequest(Socket socket) throws IOException, EmptyRequestException {
        this.socket = socket;
        //1.1解析请求行
        parseRequestLine();
        //1.2解析消息头
        parseHeaders();
        //1.3解析消息正文
        parseContent();

        }

    /*
     * 解析请求行
     */
    private void parseRequestLine() throws IOException, EmptyRequestException {
        //1.1、解析请求行
        String line = readLine();
        System.out.println(line);
        /*
         * 将请求行内容通过空格拆分并分别赋值给三个变量
         * 正则表达式中"\s"表示空格,但是"\"需要转义,所以使用"\\s"
         * [GET,/myweb/index.html,HTTP/1.1]
         */
        String[] data = line.split("\\s");
        method = data[0];
        uri = data[1];
        protocol = data[2];
        parseUri();//二次解析抽象路径
        System.out.println("请求方式:" + method);
        System.out.println("抽象路径:" + uri);
        System.out.println("协议版本:" + protocol);
    }

    /*
     * 进一步解析uri抽象路径
     */
    private void parseUri() {
        /*
         * uri是根据浏览器发送的请求解析出来的,而uri有两种情况
         * 1)浏览器定位的是静态资源 localhost:8088/myweb/index.html
         *   此时uri:/myweb/index.html
         * 2)浏览器携带了参数 localhost:8088/myweb/reg?username=laoan&pwd=1234&nickname=12345&age=12
         *   此时uri:/myweb/reg?username=laoan&pwd=1234&nickname=12345&age=12
         * 所以需要解析的情况,针对于是第二种情况
         *
         * 也要注意一种情况,就是不填写表单,就提交数据
         * localhost:8088/myweb/reg?username=&pwd=&nickname=&age=
         * uri:/myweb/reg?username=&pwd=&nickname=&age=
         * requestURI = /myweb/reg
         * queryString = username=&pwd=&nickname=&age=
         * username= -> [username]
         * pwd= -> [pwd]
         * nickname= -> [nickname]
         * age= -> [age]
         * 此时在对parameters传入值,会发生数组下标越界
         *
         */
        //1.将uri根据"?"拆分
        String[] data = uri.split("\\?");
        //2.将数组中的第一个元素,赋值给requestURI,方便后续直接根据requestURI定位资源
        requestURI = data[0];
        //3.根据data的长度判定uri是否携带参数,如果长度大于1说明携带参数
        if (data.length > 1) {
            //4.将"?"右侧的内容赋值给queryString
            queryString = data[1];
            //5.将queryString根据"&"拆分成一组组的参数对
            data = queryString.split("&");
            //6.遍历data,将每一个字符串按照"="拆分
            for (String para : data) {
                String[] paras = para.split("=");
                //7.将解析的内容,按照key和valued的形式,存储到parameters中
                parameters.put(paras[0], paras.length > 1 ? paras[1] : null);
            }
        }
        System.out.println("请求部分:"+requestURI);
        System.out.println("参数部分:"+queryString);
        System.out.println("参数Map:"+parameters);
    }

    /*
     * 解析消息头
     */
    private void parseHeaders() throws IOException {
        //消息头存在多行,所以一直读取即可
        while (true) {
            String line = readLine();
            /*
             * xxxxxx: xxxx(CRLF)--readLine()-->xxxxxx: xxxx(CR)--trim()-->xxxxxx: xxxx
             * (CRLF)--readLine()-->(CR)--trim()-->空串
             */
            if (line.isEmpty()) {//如果读取的字符串是空串,说明消息头结束了
                //判断空串
                //line.length == 0
                //"".equals(line)
                break;
            }
            System.out.println("消息头:" + line);
            //将消息头根据冒号+空格拆分为消息头的名字和消息头的值,并且以key和value的形式存储进headers中
            String[] data = line.split(":\\s");
            headers.put(data[0], data[1]);
        }
    }
    /*
     * 解析消息正文
     */
    private void parseContent() {

    }
    //用于封装读取一行字符串的功能
    private String readLine() throws IOException{
        InputStream inputStream = socket.getInputStream();
        int d;
        StringBuilder stringBuilder = new StringBuilder();
        char pre = 'a';//上一次读取的字符
        char cur = 'a';//本次读取的字符
        //每次读取一个字节,也就是一个字符
        while ((d = inputStream.read()) != -1){
            //将本次读取的字符d赋值给cur
            cur = (char) d;
            //判断上一次读取的字符是否是回车符并且本次读取的是否是换行符
            if ( pre == CR && cur == LF){
                //如果判断成立,说明连续读取到了回车和换行,就直接停止读取
                break;
            }
            //说明连续读到的不是回车和换行,就将本次读取的字符拼接到builder中
            stringBuilder.append(cur);
            //将本次读取的字符赋值给上一次读取的字符,以便cur空出来,接收下一次读取的字符
            pre = cur;
            //                System.out.print( (char) d);
        }
        //由于builder中会多拼一个CR,而CR在输出时,是一个空白符,所以直接去空格即可
        String line = stringBuilder.toString().trim();
        return line;
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public String getQueryString() {
        return queryString;
    }

    /**
     * 根据给定的消息头的名字，返回对应的值
     * @return
     */
    public String getHeaders(String name) {
        return headers.get(name);
    }
}
