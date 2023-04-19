package day20230419;


/**
 * 自定义异常
 * 通常使用自定义异常表达业务错误
 * 自定义异常应该做到如下几点:
 * 1:自定义异常类名要见名知意
 *  IllegalAgeException
 *  非法的 年龄 异常
 * 2:让自定义异常类继承Exception(直接或间接均可)
 * 3:提供超类中所有的构造器
 *  alt+insert(右键,generate)-->Constructor-->ctrl+A-->enter
 */
public class IllegalAgeException extends Throwable {

    public IllegalAgeException() {
    }

    public IllegalAgeException(String message) {
        super(message);
    }

    public IllegalAgeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAgeException(Throwable cause) {
        super(cause);
    }

    public IllegalAgeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
