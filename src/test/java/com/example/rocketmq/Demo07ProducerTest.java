package com.example.rocketmq;

import com.example.rocketmq.producer.Demo07Producer;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * @description: 事务消息
 * 先投递半事务消息，等本地事务执行成功之后，给RocketMQ服务器发送二次确认，从而使消息变成可投递状态
 * 如果固定时间内未收到二次确认，则会以固定的时间间隔不断地进行消息回查，直至回查到COMMIT或ROLLBACK
 * 消息回查函数和本地事务执行函数之间需要用某种存储方位维护消息和消息状态的对应关系
 * 消息回查机制实际上也用这种方式决定是否进行消息回查
 * @author: QiuJJ
 * @create: 2020-07-29
 **/
public class Demo07ProducerTest extends RocketmqTestApplicationTests {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Demo07Producer producer;

    @Test
    public void testSyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSend(id);
        logger.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);
        new CountDownLatch(10).await();
    }
}
