package cn.javabb.design.strategy;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/12/28 18:07
 */
public interface IEnumProcessor<U,R> {

    /**
     * U传参类型，R返回值类型
     * @param u
     * @return
     */
    R process(U u);
}
