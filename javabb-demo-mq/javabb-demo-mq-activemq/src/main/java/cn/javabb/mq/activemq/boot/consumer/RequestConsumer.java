package cn.javabb.mq.activemq.boot.consumer;

import cn.hutool.core.convert.Convert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.io.Serializable;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/06/22 21:12
 */
@Component
@Slf4j
public class RequestConsumer {

    //这里先不用线程池
    @JmsListener(destination= "QUENE_REQUEST", containerFactory = "jmsQueueListener")
    public void receiveRequest(ObjectMessage objectMessage, Session session) throws JMSException {
        try {
            Serializable object = objectMessage.getObject();
           // log.info("=====================");
          //  log.info(object.toString());
            processResult(object);
          //  log.info("=====================");
            objectMessage.acknowledge();
        } catch (Exception e) {
            log.error("消费消息失败",e.getMessage());
            session.recover();
        }
    }

    /**
     * 消息处理
     * @param obj
     */
    private void processResult(Object obj) {
        System.out.println("结果处理:"+Convert.toStr(obj));
    }
}
