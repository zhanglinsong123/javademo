package com.javademo.config;

import com.javademo.constant.MqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Program: javademo
 * @Description: rabbitmq配置
 * @Author: zls
 * @Date: 2024-04-10 10:57
 **/
@Slf4j
@Configuration
public class RabbitmqConfig {

    /**
     * 延时队列交换机
     *
     * @return
     */
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        log.info("声明 delay exchange: {}", MqConstant.DELAY_EXCHANGE);
        // exchange:名字 | type:类型 | durable:是否持久化 | autoDelete:是否自动删除 | args:参数
        return new CustomExchange(MqConstant.DELAY_EXCHANGE, "x-delayed-message", true, false, args);
    }

    /**
     * 延时队列
     *
     * @return
     */
    @Bean
    public Queue delayQueue() {
        log.info("注册队列: {}", MqConstant.DELAY_QUEUE);
        // queue:名字 | durable:是否持久化 | exclusive:是否排他 | autoDelete:是否自动删除
        return new Queue(MqConstant.DELAY_QUEUE, true, false,false);
    }

    /**
     * 给延时队列绑定交换机
     *
     * @return
     */
    @Bean
    public Binding delayBinding(Queue delayQueue, CustomExchange delayExchange) {
        log.info("绑定topic: queue={},topic={},routingKey={}", delayQueue.getName(), delayExchange.getName(), MqConstant.DELAY_KEY);
        // queue:队列 | exchange:交换机 | routingKey:路由键 | noargs:参数
        return BindingBuilder.bind(delayQueue).to(delayExchange).with(MqConstant.DELAY_KEY).noargs();
    }
}
