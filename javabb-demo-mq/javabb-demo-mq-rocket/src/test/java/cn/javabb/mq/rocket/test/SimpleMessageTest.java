package cn.javabb.mq.rocket.test;

import cn.hutool.core.date.DateUtil;
import cn.javabb.mq.rocket.RocketMqDemoApplication;
import cn.javabb.mq.rocket.simple.SimpleProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/04/13 23:59
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RocketMqDemoApplication.class)
public class SimpleMessageTest {

    @Autowired
    private SimpleProducer simpleProducer;

    @Test
    public void testSyncSend() throws InterruptedException {
        String id = DateUtil.now();
        SendResult result = simpleProducer.syncSend(id);
        log.info("testSyncSend发送消息:[{}],发送成功,结果为:[{}]",id,result);
        //阻塞等待,保证消费
        new CountDownLatch(1).await();
    }
}
