package org.finalBubble.webserver.controller;

import org.finalBubble.webserver.core.ClientHandler;
import org.finalBubble.webserver.entity.User;
import org.finalBubble.webserver.http.HttpServletRequest;
import org.finalBubble.webserver.http.HttpServletResponse;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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
        String printWriterd = request.getParameter("printWriterd");
        String nickname = request.getParameter("nickname");
        String ageStr = request.getParameter("age");
        System.out.println(username + "," + printWriterd + "," + nickname + "," + ageStr);
        if ( username == null //用户名不为空
                || printWriterd == null //密码不为空
                || nickname == null //昵称不为空
                || ageStr == null //年龄不为空
                || !ageStr.matches("[0-9]+")) {//年龄不是由纯数字组成的
            File file = new File(staticDir, "/myweb/reg_fail.html");
            System.out.println(file.getAbsoluteFile());
            response.setContentFile(file);
            return;
        }
        //2、实现用户注册功能
        //2.1、将用户信息以一个User实例表示
        //将字符串的ageStr转换为int类型的age
        int age = Integer.parseInt(ageStr);
        User user = new User(username,printWriterd,nickname,age);
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
    public void login(HttpServletRequest request, HttpServletResponse response) {
        //实现用户登录功能
        System.out.println("开始实现用户登录...");
        //1、获取用户提交的表单数据
        String username = request.getParameter("username");
        String printWriterd = request.getParameter("printWriterd");
        System.out.println(username + "," + printWriterd );
        if ( username == null //用户名不为空
                || printWriterd == null ) {//密码不为空
            File file = new File(staticDir, "/myweb/login_info_error.html");
            response.setContentFile(file);
            return;
        }
        //2、判断是否用该用户文件,不存在该用户文件则不存在该用户
        File userFile = new File(USER_DIR, username + ".obj");
        if ( userFile.exists()) {
            //3、用户文件反序列化成用户对象
            try (
                    //文件字节输入流,将字节输出到指定的流对象中
                    FileInputStream fileInputStream = new FileInputStream(userFile);
                    //对象反序列化流,可以将内存中的字节数据转化为对象
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ) {
                //将指定的数据写入到对象中
                User user = (User) objectInputStream.readObject();
                //4、密码对比
                if (user.getPwd().equals(printWriterd)) {
                    //登录成功
                    File file = new File(staticDir, "/myweb/login_success.html");
                    response.setContentFile(file);
                    return;
                }
                System.out.println(user);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        File file = new File(staticDir, "/myweb/login_fail.html");
        response.setContentFile(file);
        System.out.println("用户登录功能实现完毕!!!");
    }

    /*
    public void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("开始处理用户登陆...");
        String username = request.getParameter("username");
        String printWriterd = request.getParameter("printWriterd");
        if (username == null || printWriterd == null) {
            File file = new File(staticDir, "/myweb/login_info_error.html");
            response.setContentFile(file);
            return;
        }
        File userFile = new File(USER_DIR, username + ".obj");
        //定位当前登陆的用户名的文件,如果文件存在,说明用户名没有写错
        if (userFile.exists()) {
            //反序列化该注册用户信息
            try (
                    FileInputStream fis = new FileInputStream(userFile);
                    ObjectInputStream ois = new ObjectInputStream(fis);
            ) {
                User user = (User) ois.readObject();
                if (user.getprintWriterd().equals(printWriterd)) {
                    //登陆成功
                    File file = new File(staticDir, "/myweb/login_success.html");
                    response.setContentFile(file);
                    return;
                }
                System.out.println(user);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        //程序能够执行到这里,说明用户名或者密码是错误的
        File file = new File(staticDir, "/myweb/login_fail.html");
        response.setContentFile(file);
        System.out.println("用户登录实现完毕!!!");
    }
     */

    public void showAllUser(HttpServletRequest request, HttpServletResponse response){
        System.out.println("开始生成动态页面...");
        //0、准备一个list容器，用户承装获取到的用户实例
        List<User> userList = new ArrayList<>();
        //1、将users目录下，所有的.obj文件获取File中提供的listFile方法
        //listFile() 获取当前目录下的所有子项
        File[] subFiles = USER_DIR.listFiles(f -> f.getName().endsWith(".obj"));
        //2、遍历subFiles数组，获取其中每一个obj文件，并进行反序列化
        for (File subFile : subFiles) {
            //反序列化 从文件中读取数据，还原为对象
            try (
                    //文件字节输入流,将字节输出到指定的流对象中
                    FileInputStream fileInputStream = new FileInputStream(subFile);
                    //对象反序列化流,可以将内存中的字节数据转化为对象
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ) {
                //将指定的数据写入到对象中
                userList.add((User) objectInputStream.readObject());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        //3、准备生成页面
        File file = new File("./userList.html");
        try (
                PrintWriter printWriter = new PrintWriter(file, StandardCharsets.UTF_8);
                ) {
            printWriter.println("<!DOCTYPE html>");
            printWriter.println("<html lang=\"en\">");
            printWriter.println("<head>");
            printWriter.println("<meta charset=\"UTF-8\">");
            printWriter.println("<title>用户列表</title>");
            printWriter.println("</head>");
            printWriter.println("<body>");
            printWriter.println("<center>");
            printWriter.println("<h1>用户列表</h1>");
            printWriter.println("<table border=\"1\">");
            printWriter.println("<tr>");
            printWriter.println("<th>用户名</th>");
            printWriter.println("<th>密码</th>");
            printWriter.println("<th>昵称</th>");
            printWriter.println("<th>年龄</th>");
            printWriter.println("</tr>");
            for (User user : userList) {
                printWriter.println("<tr>");
                printWriter.println("<td>" + user.getUsername() + "</td>");
                printWriter.println("<td>" + user.getPwd() + "</td>");
                printWriter.println("<td>" + user.getNickname() + "</td>");
                printWriter.println("<td>" + user.getAge() + "</td>");
                printWriter.println("</tr>");
            }
            printWriter.println("</table>");
            printWriter.println("</center>");
            printWriter.println("</body>");
            printWriter.println("</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //4.将页面作为响应正文,返回给浏览器
        response.setContentFile(file);
        System.out.println("动态页面生成完毕！！！");
    }

}
