package com.example.viewinject.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2018/08/14 0014.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewInject {

    /**
     * 定义一个传入控件id资源id的变量
     *
     * @return
     */
    int value() default -1;

}
