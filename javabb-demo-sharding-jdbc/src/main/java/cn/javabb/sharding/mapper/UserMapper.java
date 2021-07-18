package cn.javabb.sharding.mapper;

import cn.javabb.sharding.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/07/04 15:09
 */
@Mapper
public interface UserMapper{

    void save(User user);

    User get(String id);

    List<User> getList(Integer sex);

    List<User> page(@Param("address") String address,
                    @Param("page")Integer page,
                    @Param("size")Integer size);
}
