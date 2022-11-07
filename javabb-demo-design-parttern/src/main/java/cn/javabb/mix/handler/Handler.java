package cn.javabb.mix.handler;

import org.springframework.beans.factory.InitializingBean;

/**
 * 策略设计模式
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2022/10/27 23:39
 */
public interface Handler extends InitializingBean {

    public void AAA(String name);
}
