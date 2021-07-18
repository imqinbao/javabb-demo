package cn.javabb.sharding.service;

import cn.javabb.sharding.entity.User;
import cn.javabb.sharding.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/07/04 15:09
 */
@Service
public class UserService{

    @Autowired
    UserMapper userMapper;

    public boolean save(User user) {
        userMapper.save(user);
        return true;
    }

    public User get(String id) {
        return userMapper.get(id);
    }

    public List<User> getList(Integer sex) {
        return userMapper.getList(sex);
    }


    public List<User> page(String address,Integer page,Integer size) {
        return userMapper.page(address,page*size,size);
    }

}
