package cn.javabb.mq.rocket.simple;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 消費者定义消费的TOPIC是SimpleMessage.TOPIC,消费分组是simple-consumer-group-Simple-Message
 * 一般情况下:
 *  1,每个消费者分组职责单一,只消费一个TOPIC
 *  2,每个消费者分组是独占一个线程池,这样能够保证多个Topic隔离在不同的线程池,保证隔离性.
 *
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
public class SimpleConsumer implements RocketMQListener<SimpleMessage> {
    @Override
    public void onMessage(SimpleMessage simpleMessage) {
        log.info("[Consumer-onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), simpleMessage);
    }
}
