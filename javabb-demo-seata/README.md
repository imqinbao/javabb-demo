# 分布式事务
- **什么是分布式**  
指一次大的操作由不同的小操作组成的，这些小的操作分布在不同的服务器上，分布式事务需要保证这些小操作要么全部成功，要么全部失败。从本质上来说，分布式事务就是为了保证不同数据库的数据一致性。 

- **为什么要使用分布式**  
在微服务独立数据源的思想，每一个微服务都有一个或者多个数据源，虽然单机单库事务已经非常成熟，但是由于网路延迟和不可靠的客观因素，分布式事务到现在也还没有成熟的方案，对于中大型网站，特别是涉及到交易的网站，一旦将服务拆分微服务，分布式事务一定是绕不开的一个组件，通常解决分布式事务问题。

- **seata介绍**  
`seata`是阿里出的一个分布式事务解决方案组件  
**AT 模式**：参见(《Seata AT 模式》 (opens new window))文档
**TCC 模式**：参见(《Seata TCC 模式》 (opens new window))文档
**Saga 模式**：参见(《SEATA Saga 模式》 (opens new window))文档
**XA 模式**：正在开发中... 目前使用的流行度情况是：AT > TCC > Saga。因此，我们在学习Seata的时候，可以花更多精力在AT模式上，最好搞懂背后的实现原理，毕竟分布式事务涉及到数据的正确性，出问题需要快速排查定位并解决。
# 下载地址
Github地址:[https://github.com/seata/seata/](https://github.com/seata/seata/)  
1.4.1下载地址: [https://github.com/seata/seata/releases/tag/v1.4.1](https://github.com/seata/seata/releases/tag/v1.4.1)
# 运行seata

运行`bin/server-start.bat`

# 开始使用
1,创建测试数据库
~~~sql
# 订单数据库信息 seata_order
DROP DATABASE IF EXISTS seata_order;
CREATE DATABASE seata_order;

DROP TABLE IF EXISTS seata_order.p_order;
CREATE TABLE seata_order.p_order
(
    id               INT(11) NOT NULL AUTO_INCREMENT,
    user_id          INT(11) DEFAULT NULL,
    product_id       INT(11) DEFAULT NULL,
    amount           INT(11) DEFAULT NULL,
    total_price      DOUBLE       DEFAULT NULL,
    status           VARCHAR(100) DEFAULT NULL,
    add_time         DATETIME     DEFAULT CURRENT_TIMESTAMP,
    last_update_time DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS seata_order.undo_log;
CREATE TABLE seata_order.undo_log
(
    id            BIGINT(20) NOT NULL AUTO_INCREMENT,
    branch_id     BIGINT(20) NOT NULL,
    xid           VARCHAR(100) NOT NULL,
    context       VARCHAR(128) NOT NULL,
    rollback_info LONGBLOB     NOT NULL,
    log_status    INT(11) NOT NULL,
    log_created   DATETIME     NOT NULL,
    log_modified  DATETIME     NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY ux_undo_log (xid, branch_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;
  
# 产品数据库信息 seata_product
DROP DATABASE IF EXISTS seata_product;
CREATE DATABASE seata_product;

DROP TABLE IF EXISTS seata_product.product;
CREATE TABLE seata_product.product
(
    id               INT(11) NOT NULL AUTO_INCREMENT,
    price            DOUBLE   DEFAULT NULL,
    stock            INT(11) DEFAULT NULL,
    last_update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS seata_product.undo_log;
CREATE TABLE seata_product.undo_log
(
    id            BIGINT(20) NOT NULL AUTO_INCREMENT,
    branch_id     BIGINT(20) NOT NULL,
    xid           VARCHAR(100) NOT NULL,
    context       VARCHAR(128) NOT NULL,
    rollback_info LONGBLOB     NOT NULL,
    log_status    INT(11) NOT NULL,
    log_created   DATETIME     NOT NULL,
    log_modified  DATETIME     NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY ux_undo_log (xid, branch_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;

INSERT INTO seata_product.product (id, price, stock)
VALUES (1, 10, 20);


# 账户数据库信息 seata_account
DROP DATABASE IF EXISTS seata_account;
CREATE DATABASE seata_account;

DROP TABLE IF EXISTS seata_account.account;
CREATE TABLE seata_account.account
(
    id               INT(11) NOT NULL AUTO_INCREMENT,
    balance          DOUBLE   DEFAULT NULL,
    last_update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS seata_account.undo_log;
CREATE TABLE seata_account.undo_log
(
    id            BIGINT(20) NOT NULL AUTO_INCREMENT,
    branch_id     BIGINT(20) NOT NULL,
    xid           VARCHAR(100) NOT NULL,
    context       VARCHAR(128) NOT NULL,
    rollback_info LONGBLOB     NOT NULL,
    log_status    INT(11) NOT NULL,
    log_created   DATETIME     NOT NULL,
    log_modified  DATETIME     NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY ux_undo_log (xid, branch_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;
INSERT INTO seata_account.account (id, balance)
VALUES (1, 50);

~~~
其中，每个库中的undo_log表，是Seata AT模式必须创建的表，主要用于分支事务的回滚。
另外，考虑到测试方便，我们插入了一条id = 1的account记录，和一条id = 1的product记录。

2,引入依赖  
**seata依赖**
~~~xml
<dependency>
    <groupId>io.seata</groupId>
    <artifactId>seata-spring-boot-starter</artifactId>
    <version>1.4.1</version>
</dependency>
~~~
**多数据源依赖**
~~~xml
<!--    多数据源    -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
    <version>${dynamic-ds.version}</version>
</dependency>
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
</dependency>
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
</dependency>
~~~
3,服务配置  
~~~yaml
# Tomcat
server:
  port: 9003
# Spring
spring:
  application:
    # 应用名称
    name: javaitem-demo-seata
  profiles:
    # 环境配置
    active: dev
  redis:
    host: localhost
    port: 6379
    password:
  datasource:
    dynamic:
      primary: master
      strict: true
      seata: true    #开启seata代理，开启后默认每个数据源都代理，如果某个不需要代理可单独关闭
      seata-mode: AT #支持XA及AT模式,默认AT
      datasource:
        # 主库数据源
        master:
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://127.0.0.1:3306/javaitem-cloud?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8
          username: root
          password: 123456
          seata: false
        # seata_order数据源
        order:
          username: root
          password: 123456
          url: jdbc:mysql://127.0.0.1:3306/seata_order?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8
          driver-class-name: com.mysql.cj.jdbc.Driver
        # seata_account数据源
        account:
          username: root
          password: 123456
          url: jdbc:mysql://127.0.0.1:3306/seata_account?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8
          driver-class-name: com.mysql.cj.jdbc.Driver
        # seata_product数据源
        product:
          username: root
          password: 123456
          url: jdbc:mysql://127.0.0.1:3306/seata_product?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8
          driver-class-name: com.mysql.cj.jdbc.Driver

# seata配置
seata:
  enabled: true
  application-id: applicationName
  tx-service-group: my_test_tx_group
  #一定要是false
  enable-auto-data-source-proxy: false
  service:
    vgroup-mapping:
      #key与上面的tx-service-group的值对应
      my_test_tx_group: default
    grouplist:
      #seata-server地址仅file注册中心需要
      default: localhost:8091
  config:
    type: file
  registry:
    type: file

# Mybatis-plus配置
mybatis-plus:
  mapper-locations: classpath:cn/javabb/**/*Mapper.xml
  typeAliasesPackage: cn.javabb.**.entity
  global-config:
    id-type: 0
    field-strategy: 1
    db-column-underline: true
    logic-delete-value: 1
    logic-not-delete-value: 0
  configuration:
    map-underscore-to-camel-case: true
    cache-enabled: false

logging.level.org.springframework.boot.autoconfigure: error
# swagger配置
swagger:
  title: seate-demo
  license: Powered By javabb
  licenseUrl: http://javabb.cn

~~~
4,测试事务回滚
~~~java
/**
 * 正常下单
 */
@Test
public void test1() {
    orderService.placeOrder(new PlaceOrderRequest(1, 1, 2));
}
/**
 * 模拟库存不足
 */
@Test
public void test2() {
    orderService.placeOrder(new PlaceOrderRequest(1, 1, 22));
}
/**
 * 模拟余额不足
 */
@Test
public void test3() {
    orderService.placeOrder(new PlaceOrderRequest(1, 1, 6));
}
~~~

5,嵌套事务回滚
修改OrderService.java 
~~~java
// 扣减库存并计算总价
Double totalPrice = productService.reduceStock(productId, amount);
// 扣减余额
accountService.reduceBalance(userId, totalPrice);
// 在前面的基础上直接扣掉100.0  
accountService.reduceBalance(userId, 100.0);
~~~
测试
~~~java
/**
 * 正常下单
 */
@Test
public void test1() {
    orderService.placeOrder(new PlaceOrderRequest(1, 1, 2));
}
~~~
测试正常下单,发现最后的回滚状态是回到最初的数据.