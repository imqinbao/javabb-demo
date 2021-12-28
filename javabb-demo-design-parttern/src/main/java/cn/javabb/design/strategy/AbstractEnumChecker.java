package cn.javabb.design.strategy;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/12/28 18:09
 */
public abstract class AbstractEnumChecker <T extends Enum<T>,U,R> implements IEnumChecker<T> {
    {
        this.registerCheck();
    }

    @Override
    public void registerCheck() {
        EnumProcessorRegister<T,U,R> register = register();
        validateAllEnumItemMatchProcessor(register);
    }

    private void validateAllEnumItemMatchProcessor(EnumProcessorRegister<T,U,R> register) {
        Class<T> enumType = getEnumType();
        T[] enumConstants = enumType.getEnumConstants();
        for (T enumConstant : enumConstants) {
            boolean hasHandler = register.isHasHandler(enumConstant);
            if (!hasHandler){
                throw new RuntimeException(this.getClass().getSimpleName() + " don't config all enum processor,"+ enumConstant.name() + " is missing");
            }
        }
    }
    protected abstract EnumProcessorRegister<T,U,R> register();
}
