package org.finalBubble.webserver.http;


/**
 *  空请求异常
 *  1: 异常名要见明知义 Empty 空的 Request 请求 Exception 异常
 *  2: 自定义异常类需要直接或者间接继承Exception
 *  3: 按照父类要求,生成对应的构造方法 alt+insert
 */
public class EmptyRequestException extends Exception{
    public EmptyRequestException() {
    }

    public EmptyRequestException(String message) {
        super(message);
    }

    public EmptyRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyRequestException(Throwable cause) {
        super(cause);
    }

    public EmptyRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
