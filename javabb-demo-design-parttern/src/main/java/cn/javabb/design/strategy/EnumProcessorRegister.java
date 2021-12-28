package cn.javabb.design.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/12/28 18:09
 */
public class EnumProcessorRegister<T extends Enum<T>,U,R> {

    private Map<T, IEnumProcessor<U,R>> container = new HashMap<>();

    /**
     * 将枚举t和处理策略processor绑定
     */
    public EnumProcessorRegister<T,U,R> register(T t, IEnumProcessor<U,R> processor){
        container.put(t,processor);
        return this;
    }

    /**
     * 获取枚举对应的处理策略
     * @param t
     * @return
     */
    public IEnumProcessor<U,R> getProcessor(T t){
        return container.get(t);
    }
    /**
     * 判断枚举项是否绑定了处理策略
     */
    public boolean isHasHandler(T t){
        IEnumProcessor<U,R> iEnumProcessor = container.get(t);
        if (iEnumProcessor != null){
            return true;
        }
        return false;
    }
}
