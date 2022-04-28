## mybatis-plus地址
https://baomidou.com/

~~~sql
CREATE TABLE `javabb-demo`.`t_user`  (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '用户名',
  `age` tinyint(2) NULL COMMENT '年龄',
  `user_type` tinyint(1) NULL COMMENT '用户类型 1游客 2普通用户 3vip用户',
  `address` varchar(255) NULL COMMENT '地址',
  `create_date` datetime NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);
~~~