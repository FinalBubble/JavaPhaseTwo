package day20230528;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AutoRunMethod {
    /**
     * 为注解添加参数
     * 语法：
     * 类型  参数名
     * 1)   如果注解只有一个参数时，建议参数名使用value
     *      如果注解声明了参数，在使用注解时，就必须穿参数
     *      传参数时，直接在注解的后面用
     *      @AutoRunMethod(value = 1)
     *      因为使用value，也可以不写参数名
     *      @AutoRunMethod(1)
     *      @return
     * 2)   注解中,也可以声明多个参数,在传参时,参数顺序无所谓,参数名不能省略
     *      @AutoRunMethod(value = 1, info = "a")
     *      @AutoRunMethod(info = "b", num = 2)
     * 3)   参数是可以设置默认值的
     *      类型 参数名() [default 默认值];
     *      表示如果不传入参数,改参数就会使用默认值
     */

    int num() default 1;

    //String info();
}
