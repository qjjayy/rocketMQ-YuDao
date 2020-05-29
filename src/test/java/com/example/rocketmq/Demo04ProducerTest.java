package com.example.rocketmq;

import com.example.rocketmq.producer.Demo04Producer;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * @description: 消息重试、死信队列
 * 每次重试的时间间隔不同，第一次重试消费按照延迟级别为3开始，默认为16次重试消费，到达最高延迟级别为18
 * 只有集群模式下，才有重试消费
 * @author: QiuJJ
 * @create: 2020-05-29
 **/
public class Demo04ProducerTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Demo04Producer producer;

    @Test
    public void testSyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSend(id);
        logger.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);
        new CountDownLatch(30).await();
    }
}
