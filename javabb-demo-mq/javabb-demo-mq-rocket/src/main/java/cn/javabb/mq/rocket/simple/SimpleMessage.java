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
    /**
     * 编号
     */
    private String id;
}
