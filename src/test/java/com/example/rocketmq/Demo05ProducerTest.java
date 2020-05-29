package com.example.rocketmq;

import com.example.rocketmq.producer.Demo04Producer;
import com.example.rocketmq.producer.Demo05Producer;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * @description: 广播消费消息，每个消费者实例都接收全量的消息
 * @author: QiuJJ
 * @create: 2020-05-29
 **/
public class Demo05ProducerTest extends RocketmqTestApplicationTests {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Demo05Producer producer;

    @Test
    public void test() throws InterruptedException {
        new CountDownLatch(10).await();
    }

    @Test
    public void testSyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSend(id);
        logger.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);
        new CountDownLatch(10).await();
    }
}
