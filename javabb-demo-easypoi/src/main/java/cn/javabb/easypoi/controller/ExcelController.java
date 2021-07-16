package cn.javabb.easypoi.controller;

import cn.javabb.easypoi.model.UserInfo;
import cn.javabb.easypoi.util.ExcelUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/06/29 21:39
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    /**
     * 浏览器输入地址直接下载
     * @param response
     * @throws Exception
     */
    @GetMapping("/exportModel")
    public void exportModel(HttpServletResponse response) throws Exception {
        UserInfo user1 = new UserInfo();
        user1.setId(1L);
        user1.setUserName("张三");
        user1.setAddress("湖北武汉");
        user1.setBirthday(new Date());

        UserInfo user2 = new UserInfo();
        user2.setId(2L);
        user2.setUserName("张三1");
        user2.setAddress("湖北武汉1");
        user2.setBirthday(new Date());
        List<UserInfo> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);

        ExcelUtil.exportExcel(list,"用户列表","用户列表", UserInfo.class,"用户列表",true,response);
    }

}
