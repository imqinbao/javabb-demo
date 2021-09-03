package cn.javabb.poi.easypoi.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/06/29 21:08
 */
@Data
public class UserInfo implements Serializable {
    @Excel(name = "Id",orderNum = "1", width = 25)
    private Long id;
    @Excel(name = "用户名",orderNum = "2", width = 25)
    private String userName;
    @Excel(name = "生日",format = "yyyy-MM-dd HH:mm:ss",orderNum = "3", width = 25)
    private Date birthday;
    @Excel(name = "地址",orderNum = "4", width = 25)
    private String address;
    //头像
    @Excel(name = "头像",orderNum = "5", width = 25)
    private String avatar;
}
