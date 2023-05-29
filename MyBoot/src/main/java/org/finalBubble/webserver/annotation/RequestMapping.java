package org.finalBubble.webserver.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记哪些方法是业务方法，并且指定调用方法的抽象路径的值
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestMapping {
    //用于接收调用业务方法的抽象路径
    String value();
}
