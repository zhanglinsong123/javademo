package com.javademo.feign;

import com.javademo.annotation.BackOff;
import com.javademo.annotation.FeignRetry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-03-27 16:32
 **/
@FeignClient(name = "customFeignClient", url = "http://www172168000036.pinwheel.qycloud.com.cn:39000/feginRetryTest")
public interface CustomFeignClient {
    @GetMapping("/hello")
    @FeignRetry(maxAttempts = 6, backOff = @BackOff(delay = 500L, maxDelay = 20000L, multiplier = 4), include = {RuntimeException.class})
    Map<String, Object> hello();
}
