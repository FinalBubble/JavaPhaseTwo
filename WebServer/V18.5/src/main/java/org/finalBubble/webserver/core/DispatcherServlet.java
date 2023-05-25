package org.finalBubble.webserver.core;

import org.finalBubble.webserver.controller.ArticleController;
import org.finalBubble.webserver.controller.UserController;
import org.finalBubble.webserver.http.HttpServletRequest;
import org.finalBubble.webserver.http.HttpServletResponse;

import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理请求的环节
 */
public class DispatcherServlet {



    //静态资源不能调用非静态资源
    private static File staticDir;
    //static代码块会在类加载的时候,被执行,并且其中的内容,只会被执行一次
    static {
        try {
            //专门定位static目录
            staticDir = new File(ClientHandler.class.getClassLoader().getResource(
                    "./static").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void service(HttpServletRequest request, HttpServletResponse response){
        /*
         * http://localhost:8088/myweb/index.html
         * 这个URL中的抽象路径uri:/myweb/index.html
         * 而我们要利用这个路径定位当前项目中static目录下的内容
         * static/myweb/index.html
         * localhost:8088/
         */
        String path = request.getRequestURI();
        System.out.println("抽象路径:" + path);
        /*
         * 不带参数: /myweb/index.html -> 获取对应的静态资源
         * 带参数:/myweb/reg -> 调用对应的业务方法
         */
        //通过path判断本次请求是否是一个业务
        if ("/myweb/reg".equals(path)){//如果path是"/myweb/reg",说明一定是注册功能
            UserController userController = new UserController();
            userController.reg(request,response);
        } else if ("/myweb/login".equals(path)) {
            UserController userController = new UserController();
            userController.login(request,response);
        }else if ("/myweb/writeArticle".equals(path)) {
            ArticleController articleController = new ArticleController();
            articleController.writeArticle(request,response);
        } else {
            /*
             * File(File parent,String sub)
             * 该方法可以通过parent父级目录,定位sub子级资源
             * 并且如果子级资源不存在,不会报空指针
             * 当前file实例,就可以表示用户通过URL想要获取的资源
             * 此处有两种情况:
             * 1:用户想要获取的资源真实存在 localhost:8088/myweb/index.html
             *   将存在的资源直接返回给客户端
             *   "HTTP/1.1 200 OK"
             * 2:用户想要获取的资源不存在
             *  2.1:该资源文件不存在 localhost:8088/myweb/a.html
             *  2.2:用户输入的是一个目录 localhost:8088/myweb
             *   此时,用户访问不到想要获取的资源,因为服务器中没有可以返回的资源,
             *   但是为了客户的良好体验,应该统一返回一个root/404.html页面
             *   此处注意:如果客户端访问的资源不存在,服务器需要返回的响应信息的状态行,
             *   需要发生变化 "HTTP/1.1 404 NotFound"
             */

            File file = new File(staticDir, path);
            String line;
            if (file.isFile()) {
                //此处无须设置状态码和描述短语,但是需要将定位的资源发送给response作为响应正文
                response.setContentFile(file);
            } else {//不是文件或者是一个目录
                response.setStatusCode(404);
                response.setStatusReason("NotFound");
                //代码执行到此处,说明要返回给客户端一个404页面,所以需要将file定位到404页面
                file = new File(staticDir,"/root/404.html");
                response.setContentFile(file);
            }
        }
    }
}
