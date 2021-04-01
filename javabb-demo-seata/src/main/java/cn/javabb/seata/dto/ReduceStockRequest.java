package cn.javabb.seata.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/03/30 16:48
 */
@Data
@Builder
public class ReduceStockRequest {

    private Integer productId;

    private Integer amount;
}
