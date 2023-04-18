package day20230418;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 使用当前类，测试对象流
 */
public class Person implements Serializable {

    //固定死了UID值
    static final long serialVersionUID = 2205169088471772871L;

    private String name;
    private int age;
    private String gender;//性别
    private transient String[] otherInfo;//其余信息
    //新增属性
    private double salary;

    /*
    * transient 修饰一个属性后，当进行对象的序列化时，改属性的值将会被忽略
    * 忽略一些不必要的属性值，进行对象的瘦身，可以提高程序的响应速度，减少资源的开销
    *
    * */

    public Person(String name, int age, String gender, String[] otherInfo) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.otherInfo = otherInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String[] getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String[] otherInfo) {
        this.otherInfo = otherInfo;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", otherInfo=" + Arrays.toString(otherInfo) +
                '}';
    }

}
