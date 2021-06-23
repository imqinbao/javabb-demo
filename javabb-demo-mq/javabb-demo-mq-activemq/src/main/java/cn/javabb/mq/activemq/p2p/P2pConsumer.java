package cn.javabb.mq.activemq.p2p;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/06/22 19:59
 */
public class P2pConsumer {

    //连接信息
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME,
                PASSWORD,BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("queue");
        MessageConsumer consumer = session.createConsumer(destination);
        while (true){
            TextMessage message = (TextMessage) consumer.receive();
            if (message==null){
                break;
            }
            System.out.println(message.getText());
        }
        if(connection!=null){
            connection.close();
        }
    }
}
