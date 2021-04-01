package cn.javabb.seata.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/03/30 16:47
 */
@Data
@Builder
public class ReduceBalanceRequest {
    private Integer userId;

    private Integer price;
}
