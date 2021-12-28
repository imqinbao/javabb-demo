package cn.javabb.design.strategy.handler;

import cn.javabb.design.strategy.AbstractEnumMatcher;
import cn.javabb.design.strategy.EnumProcessorRegister;
import cn.javabb.design.strategy.enums.PayStatusEnum;
import cn.javabb.design.strategy.qry.OrderQry;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/12/28 18:30
 */
public class PayStatusEnumMatcher extends AbstractEnumMatcher<PayStatusEnum, OrderQry,Boolean> {

    /**
     * 将不同的枚举项和处理策略注册上
     * @return
     */
    @Override
    protected EnumProcessorRegister<PayStatusEnum, OrderQry, Boolean> register() {
        return new EnumProcessorRegister<PayStatusEnum,OrderQry, Boolean>()
                .register(PayStatusEnum.SUCCESS,new PayStatusEnumHandler())
                .register(PayStatusEnum.FAIL,new PayStatusEnumHandler())
                .register(PayStatusEnum.ING,new PayStatusEnumHandler());
    }

    /**
     * 返回要处理的枚举类型
     * @return
     */
    @Override
    public Class<PayStatusEnum> getEnumType() {
        return PayStatusEnum.class;
    }
}
