package com.javademo.controller;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import com.javademo.component.sensitiveword.MyWordReplace;
import com.javademo.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Program: javademo
 * @Description: sensitive-word敏感词工具测试
 * @Author: zls
 * @Date: 2024-04-02 15:29
 **/
@RestController
public class SensitiveWordTestController {

    @Autowired
    private SensitiveWordBs sensitiveWordBs;

    /**
     * 自定义敏感词替换测试接口
     * @param content
     * @return
     */
    @GetMapping("/sensitive/customReplace")
    public JsonData customReplace(@RequestParam("content") String content) {
        return JsonData.buildSuccess(SensitiveWordHelper.replace(content, new MyWordReplace()));
    }

    /**
     * 自定义黑名单敏感词查找测试接口
     * @param content
     * @return
     */
    @GetMapping("/sensitive/findAll")
    public JsonData findAll(@RequestParam("content") String content) {
        return JsonData.buildSuccess(sensitiveWordBs.findAll(content));
    }

    /**
     * 自定义验证字符串是否包含敏感词
     * @param content
     * @return
     */
    @GetMapping("/sensitive/contains")
    public JsonData contains(@RequestParam("content") String content) {
        return JsonData.buildSuccess(sensitiveWordBs.contains(content));
    }
}
