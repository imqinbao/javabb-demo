package cn.javabb.design.strategy.enums;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/12/28 18:21
 */
public enum PayStatusEnum {

    // 1=成功，0=队列中，暂未处理，-1=失败
    SUCCESS(1, "已打款成功"),
    FAIL(-1, "打款失败"),
    ING(0, "队列中，暂未处理");

    private Integer code;
    private String desc;

    PayStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
