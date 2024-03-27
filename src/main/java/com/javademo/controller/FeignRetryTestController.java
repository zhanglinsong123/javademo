package com.javademo.controller;

import com.javademo.feign.CustomFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Program: javademo
 * @Description: FeignRetry调用重试测试
 * @Author: zls
 * @Date: 2024-03-27 16:31
 **/
@RestController
public class FeignRetryTestController {

    @Resource
    private CustomFeignClient customFeignClient;

    @GetMapping("feignRetryTest")
    public void feignRetryTest() {
        customFeignClient.hello();
    }

}
