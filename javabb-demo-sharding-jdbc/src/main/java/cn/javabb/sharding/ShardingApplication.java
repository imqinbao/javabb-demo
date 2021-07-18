package cn.javabb.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/07/18 14:56
 */
@MapperScan("cn.javabb.sharding.mapper")
@SpringBootApplication
public class ShardingApplication {
    public static void main(String[] args) {

        SpringApplication.run(ShardingApplication.class, args);
    }
}
