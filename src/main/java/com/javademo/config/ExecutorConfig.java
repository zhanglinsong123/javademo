package com.javademo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Program: javademo
 * @Description: 线程池配置
 * @Author: zls
 * @Date: 2024-03-29 10:40
 **/
@Configuration
@EnableAsync
@Slf4j
public class ExecutorConfig {

    @Bean("exportDataExecutor")
    public ThreadPoolTaskExecutor exportDataExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数量：当前机器的核心数
        executor.setCorePoolSize(
                Runtime.getRuntime().availableProcessors());
        // 最大线程数
        executor.setMaxPoolSize(
                Runtime.getRuntime().availableProcessors());
        // 队列大小
        executor.setQueueCapacity(200);
        // 线程池中的线程名前缀
        executor.setThreadNamePrefix("exportDataExecutor-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程（主线程）来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 执行初始化
        executor.initialize();
        return executor;
    }
}
