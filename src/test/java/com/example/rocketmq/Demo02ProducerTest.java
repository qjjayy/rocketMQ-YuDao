package com.example.rocketmq;

import com.example.rocketmq.producer.Demo02Producer;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @description: 批量发送消息
 * @author: QiuJJ
 * @create: 2020-05-29
 **/
public class Demo02ProducerTest extends RocketmqTestApplicationTests {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Demo02Producer producer;

    @Test
    public void testSendBatch() throws InterruptedException {
        List<Integer> ids = Arrays.asList(1, 2, 3);
        SendResult result = producer.sendBatch(ids);
        logger.info("[testSendBatch][发送编号：[{}] 发送结果：[{}]]", ids, result);
        new CountDownLatch(10).await();
    }
}
