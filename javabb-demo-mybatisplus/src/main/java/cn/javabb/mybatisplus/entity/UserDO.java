package cn.javabb.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2022/04/23 00:19
 */
@Data
@TableName("t_user")
public class UserDO {

    @TableId(value = "id",type= IdType.ASSIGN_ID)
    private String id;

    private String name;

    private String address;

    private Integer age;

    private Integer userType;

    private Date createDate;

}
