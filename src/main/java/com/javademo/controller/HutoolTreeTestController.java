package com.javademo.controller;

import com.javademo.service.HutoolTreeService;
import com.javademo.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Program: javademo
 * @Description: hutool tree 工具测试
 * @Author: zls
 * @Date: 2024-04-01 13:23
 **/
@RestController
public class HutoolTreeTestController {

    @Autowired
    private HutoolTreeService hutoolTreeService;


    /**
     * hutool生成树形结构测试接口
     * @return
     */
    @GetMapping("/tree")
    public JsonData tree() {
        return JsonData.buildSuccess(hutoolTreeService.listWithTree());
    }
}
