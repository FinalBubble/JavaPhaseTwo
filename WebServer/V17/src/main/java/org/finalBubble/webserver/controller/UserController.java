package org.finalBubble.webserver.controller;

import org.finalBubble.webserver.core.ClientHandler;
import org.finalBubble.webserver.entity.User;
import org.finalBubble.webserver.http.HttpServletRequest;
import org.finalBubble.webserver.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;

/**
 * 专门用于处理用户相关的业务操作
 * MVC设计模式
 * M: model 模型
 * V: view 视图
 * C: controller 控制
 */
public class UserController {
    //加载类的时候,实例化一个users目录的File实例
    private static File USER_DIR = new File("./WebServer/users");
    private static File staticDir;
    static {
        //目录不存在时,才会创建该目录 exists方法,判断是否存在,存在返回true,不存在返回false
        if (!USER_DIR.exists()) {
            USER_DIR.mkdir();
        }
        try {
            //专门定位static目录
            staticDir = new File(ClientHandler.class.getClassLoader().getResource(
                    "./static").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /*
     * 处理用户注册的业务方法
     */
    public void reg(HttpServletRequest request, HttpServletResponse response){
        //实现用户注册功能
        System.out.println("开始实现用户注册...");
        //1、获取用户提交的表单数据
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        String nickname = request.getParameter("nickname");
        String ageStr = request.getParameter("age");
        System.out.println(username + "," + pwd + "," + nickname + "," + ageStr);
        if ( username == null //用户名不为空
                || pwd == null //密码不为空
                || nickname == null //昵称不为空
                || ageStr == null //年龄不为空
                || !ageStr.matches("[0-9]+")) {//年龄不是由纯数字组成的
            File file = new File(staticDir, "/myweb/reg_fail.html");
            response.setContentFile(file);
            return;
        }
        //2、实现用户注册功能
        //2.1、将用户信息以一个User实例表示
        //将字符串的ageStr转换为int类型的age
        int age = Integer.parseInt(ageStr);
        User user = new User(username,pwd,nickname,age);
        //2.2将user序列化到文件中
        //new File(父级目录路径,子级文件路径);
        //规定序列化的文件以注册的用户名作为文件名,并且文件的后缀为.obj
        File userFile = new File(USER_DIR, username + ".obj");
        if (userFile.exists()) {
            File file = new File(staticDir, "/myweb/reg_have_user.html");
            response.setContentFile(file);
            return;
        }
        try (
                //文件字节输出流,将字节输出到指定的文件中
                FileOutputStream fileOutputStream = new FileOutputStream(userFile);
                //对象序列化流,可以将内存中的对象转化为字节数据
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            //将指定的对象写入到文件中
            objectOutputStream.writeObject(user);
            //注册成功
            File file = new File(staticDir, "/myweb/reg_success.html");
            response.setContentFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("用户注册功能实现完毕!!!");
    }
}
