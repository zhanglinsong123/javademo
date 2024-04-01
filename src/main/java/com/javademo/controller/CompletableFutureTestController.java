package com.javademo.controller;

import com.javademo.execption.JavaDemoException;
import com.javademo.util.CompletableFutureUtil;
import com.javademo.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @Program: javademo
 * @Description: 多线程通用工具类测试
 * @Author: zls
 * @Date: 2024-03-29 16:19
 **/
@Slf4j
@RestController
public class CompletableFutureTestController {

    @Autowired
    private CompletableFutureUtil completableFutureUtil;

    @GetMapping("/testComplatetableFuture")
    public JsonData testComplatetableFuture() {
        List<Integer> numList = completableFutureUtil.executeParallelTasks(
                Arrays.asList(1, 2, 3),
                num -> {
                    try{
                    } catch (Exception e) {
                        log.error("exception", e);
                        throw new JavaDemoException(5001, "执行异常，error:" + e.getMessage());
                    }

                    return num;
                },
                (e, s) -> {
                    log.error("发生异常，异常参数：{}，异常原因：{}", s, e.getMessage());
                    return -1;
                }
        );
        return JsonData.buildSuccess(numList);
    }
}
