package com.javademo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-03-27 16:37
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BackOff {

    long delay() default 1000;

    double multiplier() default 0.0D;

    long maxDelay() default 0L;

}
