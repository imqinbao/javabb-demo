package cn.javabb.sharding.mapper;

import cn.javabb.sharding.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/07/04 15:09
 */
public interface UserMapper extends BaseMapper<User> {

    int save(User user);

    User get(Long id);
}
