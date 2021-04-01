package cn.javabb.seata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/03/29 10:27
 */
@Data
@Builder
public class Account {
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 余额
     */
    private Double balance;

    private Date lastUpdateTime;

}
