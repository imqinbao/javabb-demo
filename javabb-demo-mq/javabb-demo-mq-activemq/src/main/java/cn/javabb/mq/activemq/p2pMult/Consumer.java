package cn.javabb.mq.activemq.p2pMult;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/06/22 20:23
 */
public class Consumer {
    //连接信息
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    //提供原子操作的Integer 提供安全操作+- 适合并发系统
    AtomicInteger count = new AtomicInteger(0);
    // 链接工厂
    ConnectionFactory connectionFactory;
    // 链接对象
    Connection connection;
    // 事务管理
    Session session;
    // 创建线程局部变量 只能被当前线程访问，其他线程无法访问和修改
    ThreadLocal<MessageConsumer> threadLocal = new ThreadLocal<>();

    public void init() {

        try {
            // 创建链接工厂
            connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
            // 从工厂中创建一个链接
            connection = connectionFactory.createConnection();
            connection.start();
            // 创建一个事务
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void getMessage(String disname) {
        try {
            Queue queue = session.createQueue(disname);
            MessageConsumer consumer = null;
            if (threadLocal.get() != null) {
                consumer = threadLocal.get();
            } else {
                consumer = session.createConsumer(queue);
                threadLocal.set(consumer);
            }

            while (true) {
                Thread.sleep(1000);
                TextMessage msg = (TextMessage) consumer.receive();
                if (msg != null) {
                    msg.acknowledge();
                    System.out.println(Thread.currentThread().getName() + ": Consumer:我是消费者，我正在消费Msg:" + msg.getText() + "--->" + count.getAndIncrement());
                } else {
                    break;
                }
            }
        } catch (JMSException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
