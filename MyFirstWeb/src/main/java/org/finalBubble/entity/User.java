package org.finalBubble.entity;

import java.io.Serializable;

/**
 * 使用当前类表示注册的用户信息
 */
public class User implements Serializable {
    //固定版本号
    static final long serialVersionUID = 42L;
    private String username;
    private String pwd;
    private String nickname;
    private int age;
    //全参构造 get set toString
    public User(String username, String pwd, String nickname, int age) {
        this.username = username;
        this.pwd = pwd;
        this.nickname = nickname;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", nickname='" + nickname + '\'' +
                ", age=" + age +
                '}';
    }
}
