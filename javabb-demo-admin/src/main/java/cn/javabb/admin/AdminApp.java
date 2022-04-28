package cn.javabb.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2022/04/28 20:29
 */
@EnableAsync
@MapperScan("cn.javabb.*.mapper")
@SpringBootApplication
public class AdminApp {

    public static void main(String[] args) {
        SpringApplication.run(AdminApp.class, args);
    }

}
