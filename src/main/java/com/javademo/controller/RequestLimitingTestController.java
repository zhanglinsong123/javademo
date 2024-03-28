package com.javademo.controller;

import com.javademo.annotation.RequestLimiting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-03-27 17:29
 **/
@RestController
public class RequestLimitingTestController {

    /**
     * 接口访问限制次数注解测试{@link RequestLimiting}
     * @return
     */
    @GetMapping("/testRequestLimiting")
    @RequestLimiting(count = 3)
    public Boolean testRequestLimiting() {
        return true;
    }
}
