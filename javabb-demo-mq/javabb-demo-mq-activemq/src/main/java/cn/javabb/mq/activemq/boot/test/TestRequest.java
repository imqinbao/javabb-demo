package cn.javabb.mq.activemq.boot.test;

import cn.javabb.mq.activemq.boot.producer.RequestQueueSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/06/23 10:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRequest {
    @Autowired
    Destination queueRequest;
    @Autowired
    RequestQueueSender queueSender;

    /**
     * 发送消息
     */
    @Test
    public void producer() {
        for(int i=0 ;i<50;i++){
            queueSender.sendMessage(queueRequest,"发送消息i="+i);
        }

    }


}
