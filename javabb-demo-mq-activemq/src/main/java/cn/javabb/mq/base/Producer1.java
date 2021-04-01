package cn.javabb.mq.base;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/10/26 22:43
 */
public class Producer1 {

    public static void main(String[] args) throws JMSException {
        // 获取mq连接工程
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        // 创建链接
        Connection createConnection = connectionFactory.createConnection();
        // 启动连接
        createConnection.start();
        // 创建会话工厂 第一个参数控制事务,第二个参数控制消息
        // AUTO_ACKNOWLEDGE 自动确认模式
        Session session = createConnection.createSession(false,Session.AUTO_ACKNOWLEDGE);



    }
}
