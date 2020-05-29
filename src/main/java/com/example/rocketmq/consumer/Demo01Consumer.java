package com.example.rocketmq.consumer;

import com.example.rocketmq.message.Demo01Message;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 建议一个消费分组只消费一个TOPIC，这样的好处是：
 * 1、每个消费分组职责单一
 * 2、每个消费分组独占一个线程池，这样能够保证多个TOPIC隔离在不同线程池，保证隔离性，从而避免一个TOPIC消费很慢，影响到另外的TOPIC的消费
 * */
@Component
@RocketMQMessageListener(topic = Demo01Message.TOPIC, consumerGroup = "demo01-consumer-group-" + Demo01Message.TOPIC)
public class Demo01Consumer implements RocketMQListener<Demo01Message> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(Demo01Message message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
