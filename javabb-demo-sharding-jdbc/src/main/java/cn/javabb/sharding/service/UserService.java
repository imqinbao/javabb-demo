package cn.javabb.sharding.service;

import cn.javabb.sharding.entity.User;
import cn.javabb.sharding.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/07/04 15:09
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    public boolean save(User user) {
        return baseMapper.save(user)>0;
    }

    public User get(Long id) {
        return baseMapper.get(id);
    }
}
