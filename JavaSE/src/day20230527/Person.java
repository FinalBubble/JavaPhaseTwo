package day20230527;

import day20230528.AutoRunClass;
import day20230528.AutoRunMethod;

/**
 * 使用当前类来测试反射机制
 */
@AutoRunClass
public class Person {
    private String name = "张三";
    private int age = 22;

    //无参构造 全参构造
    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @AutoRunMethod(num = 1)
    public void sayHello() {
        System.out.println(name + ":hello!!");
    }

    public void sayHi() {
        System.out.println(name + ":hi!!");
    }


    public void sayGoodBye() {
        System.out.println(name + ":bye!!");
    }

    public void doSome(String thing) {
        System.out.println(name + "正在" + thing);
    }

    public void doSome(String thing, int sum) {
        for (int i = 0; i < sum; i++) {
            System.out.println(name + "正在" + thing);
        }
    }

    private void secret() {
        System.out.println(name + ":这是我的私有方法!");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}