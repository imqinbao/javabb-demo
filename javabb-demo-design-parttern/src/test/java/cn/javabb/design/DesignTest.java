package cn.javabb.design;

import cn.javabb.design.strategy.enums.PayStatusEnum;
import cn.javabb.design.strategy.handler.PayStatusEnumMatcher;
import cn.javabb.design.strategy.qry.OrderQry;
import org.junit.Test;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/12/28 18:39
 */
public class DesignTest {

    @Test
    public void strategy() {
        OrderQry orderQry = new OrderQry();
        orderQry.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        Boolean result = new PayStatusEnumMatcher().matchAndProcess(PayStatusEnum.FAIL, orderQry);
        System.out.println(result);
    }

}
