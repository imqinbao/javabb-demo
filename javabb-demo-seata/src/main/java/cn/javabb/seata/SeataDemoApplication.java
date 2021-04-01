package cn.javabb.seata;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/03/29 10:00
 */
@Slf4j
@MapperScan("cn.javabb.seata.mapper")
@SpringBootApplication
public class SeataDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataDemoApplication.class, args);
        log.info("打开浏览器:http://localhost:9003/doc.html");
    }
}
