package cn.javabb.common.entity;

import lombok.Data;

import java.util.Date;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/09/03 22:42
 */
@Data
public class UserInfo {

    private String id;

    private String name;

    private String email;

    private Integer role;

    private Date birthday;
}
