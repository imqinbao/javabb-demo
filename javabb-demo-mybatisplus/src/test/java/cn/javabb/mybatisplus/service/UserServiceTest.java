package cn.javabb.mybatisplus.service;

import cn.javabb.mybatisplus.MybatisPlusApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2022/04/23 01:11
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisPlusApplication.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testInsertBatch() {
        userService.autoGenData(10000*100L);
    }
}