package cn.javabb.design.strategy.qry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/12/28 19:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderQry {

    private String orderId;

    private Integer payStatus;

}
