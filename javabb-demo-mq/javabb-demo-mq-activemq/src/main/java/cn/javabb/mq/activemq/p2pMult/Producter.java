package cn.javabb.mq.activemq.p2pMult;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/06/22 20:22
 */
@Slf4j
public class Producter {
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
    ThreadLocal<MessageProducer> threadLocal = new ThreadLocal<>();

    public void init() {
        try {
            System.out.println(USERNAME + "," + PASSWORD + "," + BROKER_URL);
            // 创建链接工厂
            connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
            // 从工厂中创建一个链接
            connection = connectionFactory.createConnection();
            connection.start();
            // 创建一个事务
            session = connection.createSession(true, Session.SESSION_TRANSACTED);

        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sendMessage(String disname) {
        try {
            Queue queue = session.createQueue(disname);
            MessageProducer messageProducer = null;
            if (threadLocal.get() != null) {
                System.out.println("当前threadLocal不为空");
                messageProducer = threadLocal.get();
            } else {
                messageProducer = session.createProducer(queue);
                threadLocal.set(messageProducer);
            }
            while (true) {
                Thread.sleep(1000);
                int num = count.getAndIncrement();
                //创建一条消息
                TextMessage msg = session.createTextMessage(Thread.currentThread().getName() + "producter:我正在生产东西！，count:" + num);
                System.out.println(Thread.currentThread().getName() + "producter:我正在生产东西！，count:" + num);
                messageProducer.send(msg);
                session.commit();
            }
        } catch (JMSException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
