package cn.javabb.design.strategy;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/12/28 18:11
 */
public abstract class AbstractEnumMatcher<T extends Enum<T>,U,R> extends AbstractEnumChecker<T,U,R> implements IEnumMatcher<T,U,R> {

    @Override
    public R matchAndProcess(T t, U u) {
        // 获取注册信息
        EnumProcessorRegister<T,U,R> register = register();
        // 获取处理策略
        IEnumProcessor<U, R> processor = register.getProcessor(t);
        // 执行逻辑
        return processor.process(u);
    }
}
