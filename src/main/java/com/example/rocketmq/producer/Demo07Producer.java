package com.example.rocketmq.producer;

import com.example.rocketmq.message.Demo07Message;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class Demo07Producer {

    @Autowired
    private RocketMQTemplate rockerMQTemplate;

    public TransactionSendResult syncSend(Integer id) {
        Message<Demo07Message> message = MessageBuilder.withPayload(new Demo07Message().setId(id)).build();
        return rockerMQTemplate.sendMessageInTransaction(Demo07Message.TOPIC, message, id);
    }
}
