package cn.javabb.mq.rocket.simple;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 消費者定义消费的TOPIC是SimpleMessage.TOPIC,消费分组是simple-consumer-group-Simple-Message
 * 一般情况下:
 *  1,每个消费者分组职责单一,只消费一个TOPIC
 *  2,每个消费者分组是独占一个线程池,这样能够保证多个Topic隔离在不同的线程池,保证隔离性.
 *
 * 当出现两个消费者的consumerGroup一样的时候,这个时候消费者就是集群,集群模式下相同 Consumer Group 的每个 Consumer 实例平均分摊消息
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/04/13 23:40
 */
@Slf4j
@Component
@RocketMQMessageListener(
        topic = SimpleMessage.TOPIC,
        consumerGroup = "simple-consumer-group-" + SimpleMessage.TOPIC
)
public class SimpleConsumer_1 implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt messageExt) {
        log.info("[Consumer-onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), messageExt);
    }
}
