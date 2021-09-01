package com.shenzhijie.loguserinfo.common.utils.middleware.rockeMQ;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.common.utils.middleware.rockeMQ</p>
 * <p>ClassName:Producer</p>
 * <p>Description:TODO(事务的消息发送)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/7/13
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class Producer {
    public static void main(String[] args) throws MQClientException {
        TransactionMQProducer transactionMQProducer = new TransactionMQProducer("transactionMQGroup");
        transactionMQProducer.setNamesrvAddr("localhost:9876");
        /**设置回调*/
        transactionMQProducer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                /**执行本地事务*/
                System.out.println("=========executeLocalTransaction");
                System.out.println("message:    " + new String(message.getBody()));
                System.out.println("message:ransactionId:  " + message.getTransactionId());

                return LocalTransactionState.UNKNOW;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                /**Broker端回调,检查事务*/

                /**事务执行成功*/
                return LocalTransactionState.UNKNOW;
                /**等会儿*/
//                return LocalTransactionState.UNKNOW;
                /**消息回滚*/
//                return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        });
        transactionMQProducer.start();
        TransactionSendResult sendResult = transactionMQProducer.sendMessageInTransaction(new Message("XXXX01", "测试!这个是事务消息".getBytes(StandardCharsets.UTF_8)), null);
        System.out.println("sendResult消息发送:" + sendResult);

//        transactionMQProducer.shutdown();
        System.out.println("已经停机");
    }
}
