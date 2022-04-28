package cn.javabb.mybatisplus.service;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.javabb.concurrent.threadpool.ThreadUtil;
import cn.javabb.mybatisplus.entity.UserDO;
import cn.javabb.mybatisplus.mapper.UserMapper;
import cn.javabb.mybatisplus.util.NameUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2022/04/23 00:24
 */
@Service
public class UserService extends ServiceImpl<UserMapper, UserDO> {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void autoGenData(Long size) {
        ThreadPoolTaskExecutor taskExecutor = ThreadUtil.newThreadPool(50);
        final int pageSize = 1000;
        Long page = size % pageSize + 1;
        List<UserDO> userList = new ArrayList<>();
        for (int j = 0; j < 1000; j++) {
            for (int i = 0; i < pageSize; i++) {
                UserDO user = new UserDO();
                user.setId(IdUtil.randomUUID().replace("-",""));
                user.setName(NameUtil.autoSurAndName());
                user.setAge(RandomUtil.randomInt(18,81));
                user.setUserType(RandomUtil.randomInt(1,4));
                user.setCreateDate(RandomUtil.randomDate(DateUtil.beginOfYear(new Date()), DateField.DAY_OF_YEAR,0,365));
                user.setAddress(RandomUtil.randomString(10));
                userList.add(user);
            }
            saveBatch(userList, 1000);
        }


    }
}
