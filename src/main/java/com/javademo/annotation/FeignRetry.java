package com.javademo.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Program: javademo
 * @Description: 自定义注解
 * @Author: zls
 * @Date: 2024-03-27 16:35
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FeignRetry {
    BackOff backOff() default @BackOff();

    int maxAttempts() default 3;

    Class<? extends Throwable>[] include() default {};
}
