package cn.javabb.admin.core.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2022/04/28 21:58
 */
@Data
public class BaseDO implements Serializable {

    private Date createTime;

    private Date updateTime;
}
