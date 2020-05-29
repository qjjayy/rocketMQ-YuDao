package com.example.rocketmq;

import com.example.rocketmq.producer.Demo03Producer;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * @description: 定时消费消息
 * RocketMQ 暂时不支持任意的时间精度的延迟，而是固化了 18 个延迟级别
 * @author: QiuJJ
 * @create: 2020-05-29
 **/
public class Demo03ProducerTest  extends RocketmqTestApplicationTests {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Demo03Producer producer;

    @Test
    public void testSyncSendDelay() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSendDelay(id, 3); // 延迟级别 3 ，即 10 秒后消费
        logger.info("[testSyncSendDelay][发送编号：[{}] 发送结果：[{}]]", id, result);
        new CountDownLatch(20).await();
    }
}
