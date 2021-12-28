package cn.javabb.design.strategy.handler;

import cn.javabb.design.strategy.IEnumProcessor;
import cn.javabb.design.strategy.qry.OrderQry;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/12/28 18:21
 */
public class PayStatusEnumHandler implements IEnumProcessor<OrderQry,Boolean> {

    @Override
    public Boolean process(OrderQry qry) {
        System.out.println(qry);
        return Boolean.TRUE;
    }

}
