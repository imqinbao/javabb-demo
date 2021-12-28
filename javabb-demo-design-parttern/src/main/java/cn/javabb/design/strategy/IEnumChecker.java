package cn.javabb.design.strategy;

/**
 *
 * @desc: 校验模块
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/12/28 18:08
 */
public interface IEnumChecker<T extends Enum<T>> {
    /***
     * 校验是否所有的枚举项都绑定了处理策略
     */
    void registerCheck();

    /**
     * 要校验的枚举的类型
     * @return
     */
    Class<T> getEnumType();
}
