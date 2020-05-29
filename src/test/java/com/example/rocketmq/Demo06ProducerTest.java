package com.example.rocketmq;

import com.example.rocketmq.producer.Demo06Producer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * @description: 顺序消费消息
 * 普通顺序消息：生产者将相关联的消息发送到相同的消息队列，消费者通过同一个消费队列收到的消息是有顺序的，不同消息队列收到的消息则可能是无序的
 * 严格顺序消息：消费者收到的所有消息均是顺序的，典型应用数据库binlog
 * 消费者顺序消费消息时，是在单线程中，顺序消费每条消息
 * @author: QiuJJ
 * @create: 2020-05-29
 **/
public class Demo06ProducerTest extends RocketmqTestApplicationTests {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Demo06Producer producer;

    @Test
    public void testSyncSend() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            int id = 1024; // 固定成 1024 ，方便我们测试是否发送到相同消息队列
            SendResult result = producer.syncSend(id);
            logger.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);
        }
        new CountDownLatch(30).await();
    }

    @Test
    public void testASyncSend() throws InterruptedException {
        for (int i = 0; i < 3; i++){
            int id = 1024; // 固定成 1024 ，方便我们测试是否发送到相同消息队列
            producer.asyncSend(id, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    logger.info("[testASyncSend][发送编号：[{}] 发送成功，结果为：[{}]]", id, sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    logger.info("[testASyncSend][发送编号：[{}] 发送异常]]", id, throwable);
                }
            });
        }
        new CountDownLatch(30).await();
    }

    @Test
    public void testOnewaySend() throws InterruptedException {
        for (int i = 0; i < 3; i++){
            int id = 1024; // 固定成 1024 ，方便我们测试是否发送到相同消息队列
            producer.onewaySend(id);
            logger.info("[testOnewaySend][发送编号：[{}] 发送完成]", id);
        }
        new CountDownLatch(30).await();
    }
}