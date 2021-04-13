package cn.javabb.mq.rocket.simple;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/04/13 23:29
 */
@Slf4j
@Component
public class SimpleProducer {
    /**
     * 发送消息的时候会使用RocketMQUtil类的方法convertToRocketMessage将发送的消息序列化
     * 如何序列化:
     *  1,如果消息是String类型,则转换成byte[]内容
     *  2,如果是byte[]类型,则直接获取使用
     *  3,如果是复杂对象类型,则使用MessageConverter进行转换成字符串,如何获取字符串的byte[]内容.
     */
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 同步发送消息
     * @param id
     * @return
     */
    public SendResult syncSend(String id) {
        //创建消息
        SimpleMessage simpleMessage = new SimpleMessage();
        simpleMessage.setId(id);
        return rocketMQTemplate.syncSend(SimpleMessage.TOPIC, simpleMessage);
    }

    /**
     * 异步发送消息
     * @param id
     * @param callback
     */
    public void asyncSend(String id, SendCallback callback) {
        // 创建 Demo01Message 消息
        SimpleMessage simpleMessage = new SimpleMessage();
        simpleMessage.setId(id);
        // 异步发送消息
        rocketMQTemplate.asyncSend(SimpleMessage.TOPIC, simpleMessage, callback);
    }

    /**
     * onway发送消息
     * @param id
     */
    public void onewaySend(String id) {
        // 创建 Demo01Message 消息
        SimpleMessage simpleMessage = new SimpleMessage();
        simpleMessage.setId(id);
        // oneway 发送消息
        rocketMQTemplate.sendOneWay(SimpleMessage.TOPIC, simpleMessage);
    }

}
