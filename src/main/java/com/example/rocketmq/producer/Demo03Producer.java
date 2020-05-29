package com.example.rocketmq.producer;

import com.example.rocketmq.message.Demo03Message;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class Demo03Producer {

    @Autowired
    private RocketMQTemplate rockerMQTemplate;

    public SendResult syncSendDelay(Integer id, int delayLevel) {
        Message<Demo03Message> message = MessageBuilder.withPayload(new Demo03Message().setId(id)).build();
        return rockerMQTemplate.syncSend(Demo03Message.TOPIC, message, 30 * 1000L, delayLevel);
    }

    public void asyncSendDelay(Integer id, SendCallback callback, int delayLevel) {
        Message<Demo03Message> message = MessageBuilder.withPayload(new Demo03Message().setId(id)).build();
        rockerMQTemplate.asyncSend(Demo03Message.TOPIC, message, callback,delayLevel);
    }

}
