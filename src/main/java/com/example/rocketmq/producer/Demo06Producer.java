package com.example.rocketmq.producer;

import com.example.rocketmq.message.Demo06Message;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo06Producer {

    @Autowired
    private RocketMQTemplate rockerMQTemplate;

    public SendResult syncSend(Integer id) {
        Demo06Message message = new Demo06Message();
        message.setId(id);
        // 基于 hashKey 的哈希值取余，选择对应的队列
        return rockerMQTemplate.syncSendOrderly(Demo06Message.TOPIC, message, String.valueOf(id));
    }

    public void asyncSend(Integer id, SendCallback callback) {
        Demo06Message message = new Demo06Message();
        message.setId(id);
        rockerMQTemplate.asyncSendOrderly(Demo06Message.TOPIC, message, String.valueOf(id), callback);
    }

    public void onewaySend(Integer id) {
        Demo06Message message = new Demo06Message();
        message.setId(id);
        rockerMQTemplate.sendOneWayOrderly(Demo06Message.TOPIC, message, String.valueOf(id));
    }
}
