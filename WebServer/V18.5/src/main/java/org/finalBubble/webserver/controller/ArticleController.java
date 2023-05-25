package org.finalBubble.webserver.controller;

import org.finalBubble.webserver.core.ClientHandler;
import org.finalBubble.webserver.entity.Article;
import org.finalBubble.webserver.http.HttpServletRequest;
import org.finalBubble.webserver.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;

public class ArticleController {
    private static File ARTICLE_DIR = new File("./WebServer/articles");
    private static File staticDir;
    static {
        //目录不存在时,才会创建该目录 exists方法,判断是否存在,存在返回true,不存在返回false
        if (!ARTICLE_DIR.exists()) {
            ARTICLE_DIR.mkdir();
        }
        try {
            //专门定位static目录
            staticDir = new File(ClientHandler.class.getClassLoader().getResource(
                    "./static").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void writeArticle(HttpServletRequest request, HttpServletResponse response){
        //1、获取用户提交的表单数据
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String content = request.getParameter("content");
        if ( title == null
                || author == null
                || content == null) {
            File file = new File(staticDir, "/myweb/writeArticle_info_error.html");
            System.out.println(file.getAbsoluteFile());
            response.setContentFile(file);
            return;
        }
        Article article = new Article(title, author, content);
        File articleFile = new File(ARTICLE_DIR, title + "-" + author + ".obj");
        try (
                //文件字节输出流,将字节输出到指定的文件中
                FileOutputStream fileOutputStream = new FileOutputStream(articleFile);
                //对象序列化流,可以将内存中的对象转化为字节数据
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            //将指定的对象写入到文件中
            objectOutputStream.writeObject(article);
            //注册成功
            File file = new File(staticDir, "/myweb/writeArticle_success.html");
            response.setContentFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("用户注册功能实现完毕!!!");
    }
}
