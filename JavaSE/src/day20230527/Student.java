package day20230527;

import day20230528.AutoRunClass;
import day20230528.AutoRunMethod;

@AutoRunClass
public class Student {
    public void study() {
        System.out.println("学生:好好学习,天天向上!");
    }

    @AutoRunMethod
    public void playGame() {
        System.out.println("学生:好好游戏,才好学习!");
    }
}