package day20230419;

/**
 * 使用当前类测试异常的抛出
 */
public class Person {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws IllegalAgeException {
        if (age < 0 || age > 100) {
            //抛出一个运行时异常
//            throw new RuntimeException("您录入的年龄不合法!");
            throw new IllegalAgeException("输入的年龄不合法");
        }
        this.age = age;
    }
}
