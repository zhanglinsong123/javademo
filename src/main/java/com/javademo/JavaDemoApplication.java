package com.javademo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Program: Default (Template) Project
 * @Description: ${description}
 * @Author: zls
 * @Date: 2024-03-27 16:09
 **/
@EnableFeignClients("com.javademo.feign")
@MapperScan({"com.javademo.mapper"})
@SpringBootApplication
public class JavaDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaDemoApplication.class, args);
    }

}