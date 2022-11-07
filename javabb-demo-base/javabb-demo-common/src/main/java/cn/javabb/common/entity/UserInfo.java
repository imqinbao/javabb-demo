package cn.javabb.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/09/03 22:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private String id;

    private String name;

    private String email;

    private Integer role;

    private Date birthday;
}
