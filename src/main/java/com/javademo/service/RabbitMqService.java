package com.javademo.service;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-04-10 11:24
 **/
public interface RabbitMqService {
    /**
     * 发送延迟消息
     * @param object -- 消息内容
     * @param millisecond -- 延迟时间
     */
    void sendDelayMessage(Object object, long millisecond);
}
