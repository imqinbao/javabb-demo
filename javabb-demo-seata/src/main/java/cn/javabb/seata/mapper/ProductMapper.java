package cn.javabb.seata.mapper;

import cn.javabb.seata.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/03/30 14:10
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
