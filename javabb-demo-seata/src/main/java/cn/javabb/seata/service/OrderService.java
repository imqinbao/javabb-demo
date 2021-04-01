package cn.javabb.seata.service;

import cn.javabb.seata.dto.PlaceOrderRequest;
import cn.javabb.seata.entity.Order;
import cn.javabb.seata.mapper.OrderMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/03/30 16:10
 */
@Slf4j
@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {
    /**
     * 订单状态
     */
    public static final String ORDERE_STATUS_INIT = "INIT";
    public static final String ORDERE_STATUS_SUCCESS = "SUCCESS";
    public static final String ORDERE_STATUS_FAIL = "FAIL";

    @Resource
    private OrderMapper orderMapper;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ProductService productService;

    /**
     * 下单
     */
    @DS("order")    //  每一层都需要使用多数据源注解切换所选择的数据库
    @Transactional
    @GlobalTransactional  // 重点 第一个开启事务的需要添加seata全局事务注解
    public void placeOrder(PlaceOrderRequest request) {
        Integer userId = request.getUserId();
        Integer productId = request.getProductId();
        Integer amount = request.getAmount();
        log.info("=============ORDER START=================");
        log.info("收到下单请求,用户:{}, 商品:{},数量:{}", userId, productId, amount);

        log.info("当前 XID: {}", RootContext.getXID());
        Order order = Order.builder()
                .userId(userId)
                .productId(productId)
                .status(ORDERE_STATUS_INIT)
                .amount(amount)
                .build();

        orderMapper.insert(order);
        log.info("订单一阶段生成，等待扣库存付款中");
        // 扣减库存并计算总价
        Double totalPrice = productService.reduceStock(productId, amount);
        // 扣减余额
        accountService.reduceBalance(userId, totalPrice);

        order.setStatus(ORDERE_STATUS_SUCCESS);
        order.setTotalPrice(totalPrice);
        orderMapper.updateById(order);
        log.info("订单已成功下单");
        log.info("=============ORDER END=================");
    }
}
