package com.shenzhijie.loguserinfo.common.utils.middleware.rockeMQ;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.common.utils.middleware.rockeMQ</p>
 * <p>ClassName:OnewayProducer</p>
 * <p>Description:TODO(单向发送消息)</p>
 * 单向传输用于需要中等可靠性的情况，例如日志收集。
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/7/9
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class OnewayProducer {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("OnewayProducer");
        // Specify name server addresses.
        producer.setNamesrvAddr("localhost:9876");
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest" /* Topic */,
                    "TagA"                      /**Tag过滤消息,消息分组*/,
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            producer.sendOneway(msg);
            System.out.println(msg);
        }
        //Wait for sending to complete
        Thread.sleep(5000);
        producer.shutdown();
        System.out.println("已经停机");
    }
}
