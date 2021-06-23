package cn.javabb.mq.activemq.boot.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.Serializable;
/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/06/22 21:12
 */
@Component
public class RequestQueueSender {

    @Autowired
    private JmsTemplate jmsTemplate;
    
    public void sendMessage(Destination destination, final Serializable msg) {
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(msg);
            }
        });
    }
}
