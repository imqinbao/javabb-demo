package cn.javabb.threadpool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/07/15 17:26
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class ThreadPoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreadPoolApplication.class, args);
    }
}
