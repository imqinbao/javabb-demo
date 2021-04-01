package cn.javabb.seata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/03/30 16:47
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderRequest {

    private Integer userId;

    private Integer productId;

    private Integer amount;
}
