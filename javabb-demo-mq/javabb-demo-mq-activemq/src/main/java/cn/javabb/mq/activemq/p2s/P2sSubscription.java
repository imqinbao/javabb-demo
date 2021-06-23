package cn.javabb.mq.activemq.p2s;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/06/22 20:36
 */
public class P2sSubscription {

    //连接信息
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) throws JMSException {
        // 1、创建一个ConnectionFactory对象连接MQ服务器
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME,
                PASSWORD,BROKER_URL);
        // 2、使用工厂对象创建一个Connection对象。
        Connection connection = connectionFactory.createConnection();
        // 3、开启连接，调用Connection对象的start方法。
        connection.start();
        // 4、创建一个Session对象。
        // 第一个参数：是否开启事务。如果true开启事务，第二个参数无意义。一般不开启事务false。
        // 第二个参数：应答模式。自动应答或者手动应答。一般自动应答。
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 5、使用Session对象创建一个Destination对象。两种形式queue、topic，现在应该使用topic
        Topic topic = session.createTopic("test-topic");
        // 6、使用Session对象创建一个Producer对象。
        MessageProducer producer = session.createProducer(topic);
        // 7、创建一个Message对象，可以使用TextMessage。
        for (int i = 0; i < 50; i++) {
            TextMessage textMessage = session.createTextMessage("第" + i + "一个ActiveMQ队列目的地的消息");
            // 8、发送消息
            producer.send(textMessage);
        }
        // 9、关闭资源
        producer.close();
        session.close();
        connection.close();
    }
}
