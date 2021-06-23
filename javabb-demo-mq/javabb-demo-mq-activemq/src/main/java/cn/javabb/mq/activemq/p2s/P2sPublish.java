package cn.javabb.mq.activemq.p2s;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/06/22 20:35
 */
public class P2sPublish {

    //连接信息
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) throws JMSException, IOException {
        // 创建一个ConnectionFactory对象连接MQ服务器
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME,
                PASSWORD,BROKER_URL);
        // 创建一个连接对象
        Connection connection;
        connection = connectionFactory.createConnection();
        // 开启连接
        connection.start();
        // 使用Connection对象创建一个Session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建一个Destination对象。topic对象
        Topic topic = session.createTopic("test-topic");
        // 使用Session对象创建一个消费者对象。
        MessageConsumer consumer = session.createConsumer(topic);
        // 接收消息
        consumer.setMessageListener(new MessageListener() {

            @Override
            public void onMessage(Message message) {
                // 打印结果
                TextMessage textMessage = (TextMessage) message;
                String text;
                try {
                    text = textMessage.getText();
                    System.out.println("这是接收到的消息：" + text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }

            }
        });
        System.out.println("topic消费者启动。。。。");
        // 等待接收消息
        System.in.read();
        // 关闭资源
        consumer.close();
        session.close();
        connection.close();
    }
}
