package com.example.rocketmq.producer;

import com.example.rocketmq.message.Demo05Message;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo05Producer {

    @Autowired
    private RocketMQTemplate rockerMQTemplate;

    public SendResult syncSend(Integer id) {
        Demo05Message message = new Demo05Message();
        message.setId(id);
        return rockerMQTemplate.syncSend(Demo05Message.TOPIC, message);
    }
}
