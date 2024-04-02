package com.javademo.component.sensitiveword;

import com.github.houbb.sensitive.word.api.IWordAllow;
import com.github.houbb.sensitive.word.api.IWordDeny;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program: javademo
 * @Description: 自定义敏感词 白名单
 * @Author: zls
 * @Date: 2024-04-02 16:06
 **/
@Component
public class MyDdWordDeny implements IWordDeny {
    @Override
    public List<String> deny() {
        List<String> list = new ArrayList<>();
        list.add("你好");
        return list;
    }
}
