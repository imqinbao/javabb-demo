package cn.javabb.design.strategy;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/12/28 18:10
 */
public interface IEnumMatcher<T extends Enum<T>,U,R> {
    /**
     * 根据枚举t 执行对应的处理策略
     * @param t 枚举项
     * @param u 处理策略参数
     * @return 处理策略返回值
     */
    R matchAndProcess(T t,U u);
}
