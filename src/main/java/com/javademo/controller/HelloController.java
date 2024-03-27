package com.javademo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Program: javademo
 * @Description: hello
 * @Author: zls
 * @Date: 2024-03-27 16:20
 **/
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello() {
        return "Hello world!";
    }
}
