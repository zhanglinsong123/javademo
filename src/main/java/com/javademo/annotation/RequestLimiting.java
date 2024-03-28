package com.javademo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-03-27 17:30
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestLimiting {

    // 窗口宽度 单位：秒（默认值：一分钟）
    long period() default 60;

    // 允许最大访问次数（默认值：5次）
    long count() default 5;
}

