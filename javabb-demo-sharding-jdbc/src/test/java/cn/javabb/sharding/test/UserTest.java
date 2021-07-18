package cn.javabb.sharding.test;

import cn.javabb.sharding.entity.User;
import cn.javabb.sharding.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/07/18 14:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    UserService userService;

    /**
     * 按照性别插入到不同的表
     * 根据sex % 2 来判断,sex=0则插入到表t_user_0中,sex=1则插入到表t_user_1中
     */
    @Test
    public void testSave() {
        User user = new User().setUserName("李四").setSex(1).setAddress("湖北宜昌");
        userService.save(user);

        User user1 = new User();
        user1.setUserName("lizzy");
        user1.setAddress("湖南岳阳");
        user1.setSex(0);
        userService.save(user1);
    }

    /**
     * 查询
     */
    @Test
    public void testGet() {
        User user = userService.get("623576512011960321");
        System.out.println(user);
    }
    /**
     * 根据分表的逻辑的列查询,直接查询t_user_0表,如果为0,则查询t_user_0表
     */
    @Test
    public void testGetListBySex() {
        List<User> user = userService.getList(1);
        user.forEach(user1 -> System.out.println(user1));
    }

    /**
     * 批量插入
     */
    @Test
    public void testBatchSave() {
       for (int i = 0; i < 20; i++) {
            userService.save(new User().setUserName("张三"+i).setSex(1).setAddress("湖北武汉"+i));
        }
        for (int i = 0; i < 20; i++) {
            userService.save(new User().setUserName("Lina"+i).setSex(0).setAddress("湖北荆州"+i));
        }
    }

    /**
     * 分页
     */
    @Test
    public void testPage() {
        List<User> user = userService.page("湖北",4,10);
        user.forEach(user1 -> System.out.println(user1));
    }


}
