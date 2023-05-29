package org.finalBubble.webserver.core;

import org.finalBubble.webserver.annotation.Controller;
import org.finalBubble.webserver.annotation.RequestMapping;
import org.finalBubble.webserver.http.HttpServletRequest;
import org.finalBubble.webserver.http.HttpServletResponse;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

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
        /**
         * 扫描com.webserver.controller包下所有的类,判断哪些类被@Controller注解修饰,
         * 如果被该注解修饰,说明是业务类,则开始扫描类中的方法,并判断哪些方法被@RequestMapping注解修饰,
         * 如果被该注解修饰,则获取注解中的对应的value值,如果该值和获取的请求路径path值相同,
         * 说明就是要调用的业务方法
         */
        try {
            File dir = new File(
                    //定位到TestBoot所在包下的controller包
                    WebServerApplication.TARGET.getResource(
                            "./controller").toURI()
            );
            File[] files = dir.listFiles(f -> f.getName().endsWith(".class"));
            for (File file : files) {
                //获取文件名 UserController
                String fileName = file.getName();
                //通过文件名,去掉.class,获取类名
                String className = fileName.substring(0, fileName.indexOf("."));
                //主启动类所在的包 com
                String packageName = WebServerApplication.TARGET.getPackage().getName();
                Class cls = Class.forName(
                        packageName + ".controller." + className);
                //判断该类是否被@Controller注解修饰了
                if (cls.isAnnotationPresent(Controller.class)) {
                    Method[] methods = cls.getMethods();
                    //获取业务类中所有的方法
                    for (Method method : methods) {
                        //判断该方法是否被@RequestMapping注解修饰
                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            //获取@RequestMapping注解对象
                            RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                            //获取RequestMapping注解中传入的value属性值
                            String value = annotation.value();
                            //判断注解中的值是否和请求路径path相同,如果相同,说明要就是要调用的方法
                            if (path.equals(value)) {
                                //实例化当前Controller对象，UserController o = new UserController()
                                Object o = cls.newInstance();
                                //执行该方法，注意，要传参数 o.reg(request,response)
                                method.invoke(o,request,response);
                                //如果程序执行到这里，说明已经调用了需要执行的业务方法，就不需要再继续往下执行下去
                                return;
                            }
                        }
                    }
                }
            }
        } catch (URISyntaxException |
                 ClassNotFoundException |
                 InvocationTargetException |
                 InstantiationException |
                 IllegalAccessException e) {
            e.printStackTrace();
        }
        //请求静态资源
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
