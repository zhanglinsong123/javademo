package com.javademo.controller;

import com.javademo.mapper.TestMapper;
import com.javademo.util.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-03-28 16:14
 **/
@RestController
public class MyBatisTestController {

    @Resource
    private TestMapper testMapper;

    @GetMapping("/myBatisTest")
    public JsonData myBatisTest(){
        return JsonData.buildSuccess(testMapper.queryAll());
    }
}
