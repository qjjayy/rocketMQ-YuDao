package com.example.rocketmq.producer;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

/**
 * @description: 事务消息的执行器和回查器
 * @author: QiuJJ
 * @create: 2020-05-29
 **/
@RocketMQTransactionListener
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        // ... local transaction process, return rollback, commit or unknown
        logger.info("[executeLocalTransaction][执行本地事务，消息：{} arg：{}]", message, o);
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        // ... check transaction status and return rollback, commit or unknown
        logger.info("[checkLocalTransaction][回查消息：{}]", message);
        return RocketMQLocalTransactionState.COMMIT;
    }
}
