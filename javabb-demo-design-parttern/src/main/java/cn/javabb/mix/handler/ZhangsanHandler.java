package cn.javabb.mix.handler;

import org.springframework.stereotype.Component;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2022/10/27 23:42
 */
@Component
public class ZhangsanHandler implements Handler{
    @Override
    public void AAA(String name) {
        System.out.println("zhangsan finish task. by ZhangsanHandler");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Factory.register("zhangsan", this);
    }
}
