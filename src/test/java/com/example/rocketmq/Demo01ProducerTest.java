package com.example.rocketmq;

import com.example.rocketmq.producer.Demo01Producer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * @description: 同步发送消息、异步发送、OneWay发送、集群消费，每个消费者轮流接收消息
 * @author: QiuJJ
 * @create: 2020-05-28
 **/
public class Demo01ProducerTest extends RocketmqTestApplicationTests {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Demo01Producer producer;

    @Test
    public void testSyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSend(id);
        logger.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);
        new CountDownLatch(5).await();
    }

    @Test
    public void testASyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
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
        new CountDownLatch(5).await();
    }

    @Test
    public void testOnewaySend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.onewaySend(id);
        logger.info("[testOnewaySend][发送编号：[{}] 发送完成]", id);
        new CountDownLatch(5).await();
    }
}
