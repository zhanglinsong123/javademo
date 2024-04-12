package com.javademo.mq;

import com.javademo.constant.MqConstant;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Program: javademo
 * @Description: 延迟队列消费
 * @Author: zls
 * @Date: 2024-04-10 11:29
 **/
@Slf4j
@RabbitListener(queues = MqConstant.DELAY_QUEUE)
@Component
public class ReceiveDelayListener {
    @RabbitHandler
    public void handler(Message msgObj, String msg, Channel channel) {
        // 消息序号
        long deliveryTag = msgObj.getMessageProperties().getDeliveryTag();
        try {
            channel.basicAck(deliveryTag,false);
        } catch (IOException e) {
            /**
             * tag：消息序号
             * multiple：消息的标识，是否确认多条，false只确认当前一个消息收到，true确认所有consumer获得的消息（成功消费，消息从队列中删除
             * requeue：是否要退回到队列
             */
            try {
                channel.basicNack(deliveryTag, false, false);
            } catch (IOException ex) {
                log.error("Failed to nack message: {}", ex.getMessage());
            }
        }

        log.info("ReceiveDelayListener收到消息, time: {}, msg: {} " ,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) ,msg);
    }
}
