package cn.javabb.seata.test;

import cn.javabb.seata.dto.PlaceOrderRequest;
import cn.javabb.seata.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/04/03 15:16
 */

@RunWith(SpringRunner.class)
@MapperScan("cn.javabb.seata.mapper")
@SpringBootTest
public class OrderTest {
    @Autowired
    private OrderService orderService;

    /**
     * 模拟余额不足
     */
    @Test
    public void test1() {
        orderService.placeOrder(new PlaceOrderRequest(1, 1, 10));
    }

}
