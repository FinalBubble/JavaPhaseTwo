package day20230528;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解标注的是哪些可以被反射机制调用的类
 * 定义注解时，一般我们都会用Java中提前内置的两个注解（元注解）来修饰当前自定义的注解
 * 1) @Retention 用来制定当前注解的保留级别，有三个可选值
 *  RetentionPolicy.SOURCE          表示当前注解仅会保留在源码中，不会在字节码文件中存在
 *  RetentionPolicy.CLASS(默认值)    表示注解会保留在字节码文件中，但是反射机制不能由
 *  RetentionPolicy.RUNTIME         表示注解会保留在字节码文件中，但是反射机制可用
 * 2) @Target 用来指定当前注解在什么位置上使用，有多个可选值
 *  ElementType.TYPE     表示在类上可用
 *  ElementType.METHOD   表示在方法上可用
 *  ElementType.FIELD    表示在属性上可用
 *  ...
 *  如果自定义注解，没有声明该元注解，那么当前注解可以在任意位置使用
 *  如果自定义注解需要制定多个位置，可以使用{}包裹元素
 *  @Target({ElementType.TYPE,ElementType.METHOD})
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AutoRunClass {

}
