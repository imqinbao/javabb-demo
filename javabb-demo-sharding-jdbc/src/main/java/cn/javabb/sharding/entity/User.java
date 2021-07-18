package cn.javabb.sharding.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/07/04 15:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User {

    private String id;

    private String userName;

    private Integer sex;

    private String address;
}
