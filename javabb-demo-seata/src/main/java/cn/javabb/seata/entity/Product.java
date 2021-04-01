package cn.javabb.seata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/03/30 16:25
 */
@Data
@Builder
@TableName("product")
public class Product {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Double price;

    private Integer stock;

    private Date lastUpdateTime;
}
