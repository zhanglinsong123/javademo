package com.javademo.config;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.javademo.component.sensitiveword.MyDdWordAllow;
import com.javademo.component.sensitiveword.MyDdWordDeny;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Program: javademo
 * @Description: 自定义敏感词数据源配置类
 * @Author: zls
 * @Date: 2024-04-02 16:12
 **/
@Configuration
public class SpringSensitiveWordConfig {
    // 自定义白名单
    private final MyDdWordAllow myDdWordAllow;

    // 自定义黑名单
    private final MyDdWordDeny myDdWordDeny;

    SpringSensitiveWordConfig(MyDdWordAllow myDdWordAllow, MyDdWordDeny myDdWordDeny) {
        this.myDdWordAllow = myDdWordAllow;
        this.myDdWordDeny = myDdWordDeny;
    }

    /**
     * 初始化引导类
     * @return 初始化引导类
     * @since 1.0.0
     */
    @Bean
    public SensitiveWordBs sensitiveWordBs() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.chains(WordAllows.defaults(), myDdWordAllow))
                .wordDeny(myDdWordDeny)
                .ignoreRepeat(false)
                // 各种其他配置
                .init();

        return sensitiveWordBs;
    }
}
