package cn.javabb.admin.sys.entity;

import cn.javabb.admin.core.base.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2022/04/28 21:58
 */
@Data
@TableName("sys_user")
public class UserDO extends BaseDO {
    @TableId
    private String id;

    private String userName;

    private String nickName;

    private String userImage;

}
