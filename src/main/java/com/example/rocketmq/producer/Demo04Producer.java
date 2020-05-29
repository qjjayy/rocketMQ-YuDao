package com.example.rocketmq.producer;

import com.example.rocketmq.message.Demo04Message;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo04Producer {

    @Autowired
    private RocketMQTemplate rockerMQTemplate;

    public SendResult syncSend(Integer id) {
        Demo04Message message = new Demo04Message();
        message.setId(id);
        return rockerMQTemplate.syncSend(Demo04Message.TOPIC, message);
    }
}
