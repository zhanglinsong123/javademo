package com.javademo.service.impl;

import com.javademo.service.RabbitMqService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RabbitMqServiceImplTest {

    @Autowired
    private RabbitMqService rabbitMqService;

    @Test
    void testSendDelayMessage() {
        String message1 = "这是第一条消息";
        String message2 = "这是第二条消息";
        rabbitMqService.sendDelayMessage(message1, 10000);
        rabbitMqService.sendDelayMessage(message2, 5000);
    }

}
