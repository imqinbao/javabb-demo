package cn.javabb.mq.rocket.simple;

import lombok.Data;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/04/13 23:28
 */
@Data
public class SimpleMessage {

    public static final String TOPIC = "Simple-Message";
    public static final String PRODUCER_GROUP = "Simple-Producer-Group";
    public static final String CONSUMER_GROUP = "Simple-Consumer-Group";
    public static final String CONSUMER_GROUP_1 = "Simple-Consumer-1-Group";
    /**
     * 编号
     */
    private String id;
}
