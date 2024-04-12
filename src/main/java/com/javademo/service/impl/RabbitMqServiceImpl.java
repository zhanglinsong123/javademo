package com.javademo.service.impl;

import com.javademo.constant.MqConstant;
import com.javademo.service.RabbitMqService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-04-10 11:25
 **/
@Service
public class RabbitMqServiceImpl implements RabbitMqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendDelayMessage(Object object, long delayTime) {
        this.rabbitTemplate.convertAndSend(
                MqConstant.DELAY_EXCHANGE,
                MqConstant.DELAY_KEY,
                object.toString(),
                message -> {
                    message.getMessageProperties().setHeader("x-delay", delayTime);
                    return message;
                }
        );
    }
}
