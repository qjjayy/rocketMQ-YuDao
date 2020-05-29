package com.example.rocketmq.producer;

import com.example.rocketmq.message.Demo01Message;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo01Producer {

    @Autowired
    private RocketMQTemplate rockerMQTemplate;

    public SendResult syncSend(Integer id) {
        Demo01Message message = new Demo01Message();
        message.setId(id);
        return rockerMQTemplate.syncSend(Demo01Message.TOPIC, message);
    }

    public void asyncSend(Integer id, SendCallback callback) {
        Demo01Message message = new Demo01Message();
        message.setId(id);
        rockerMQTemplate.asyncSend(Demo01Message.TOPIC, message, callback);
    }

    public void onewaySend(Integer id) {
        Demo01Message message = new Demo01Message();
        message.setId(id);
        rockerMQTemplate.sendOneWay(Demo01Message.TOPIC, message);
    }
}
