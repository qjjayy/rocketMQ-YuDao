package com.example.rocketmq.producer;

import com.example.rocketmq.message.Demo02Message;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class Demo02Producer {

    @Autowired
    private RocketMQTemplate rockerMQTemplate;

    public SendResult sendBatch(Collection<Integer> ids) {
        List<Message> messages = new ArrayList<>(ids.size());
        for (Integer id: ids) {
            Demo02Message message = new Demo02Message().setId(id);
            messages.add(MessageBuilder.withPayload(message).build());
        }
        return rockerMQTemplate.syncSend(Demo02Message.TOPIC, messages, 30 * 1000L);
    }
}
