package cn.javabb.test.jdk8;

import cn.hutool.core.date.DateField;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.javabb.common.entity.UserInfo;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/01/05 21:02
 */
public class StreamTest {

    private static final List<UserInfo> userList = new ArrayList<>();

    static {
        UserInfo userinfo1 = new UserInfo(IdUtil.fastUUID(), "zhangsan", "zhangsan@163.com", 1, RandomUtil.randomDate(new Date(), DateField.DAY_OF_YEAR, 1, 200));
        UserInfo userinfo2 = new UserInfo(IdUtil.fastUUID(),"lisi","lisi@163.com",2, RandomUtil.randomDate(new Date(), DateField.DAY_OF_YEAR, 1, 200));
        UserInfo userinfo3 = new UserInfo(IdUtil.fastUUID(),"wangwu","wangwu@163.com",1, RandomUtil.randomDate(new Date(), DateField.DAY_OF_YEAR, 1, 200));
        UserInfo userinfo4 = new UserInfo(IdUtil.fastUUID(),"zhaoliu","zhaoliu@163.com",2, RandomUtil.randomDate(new Date(), DateField.DAY_OF_YEAR, 1, 200));
        UserInfo userinfo5 = new UserInfo(IdUtil.fastUUID(),"zhuoqi","zhuoqi@163.com",2, RandomUtil.randomDate(new Date(), DateField.DAY_OF_YEAR, 1, 200));
        UserInfo userinfo6 = new UserInfo(IdUtil.fastUUID(),"javabb","javabb@163.com",3, RandomUtil.randomDate(new Date(), DateField.DAY_OF_YEAR, 1, 200));
        Collections.addAll(userList, userinfo1, userinfo2, userinfo3, userinfo4, userinfo5, userinfo6);

    }


    public List<String> newList() {
        return Arrays.asList("a1", "b2", "c1", "c2", "d5");
    }

    // 试试水
    @Test
    public void test1() {
        userList.stream()
                .forEach(System.out::println); // for 循环打印 终端操作,流的结束操作,void类型
    }

    @Test
    public void test2() {
        // 流支持的类型主要是Collection  eg:List、Set
        // 找到第一个流
        // Stream.of()直接创建流
        Stream.of("a1", "a2", "b3").findFirst().ifPresent(System.out::println);

        // 其他原始数据类型的流 IntStream,LongSteam,DoubleStream
        IntStream.of(1, 2, 3).forEach(System.out::println);
        //上面的等同于  下面range的用法相当于 for(int i=1;i<4;i++){}
        IntStream.range(1, 4).forEach(System.out::println);

        userList.stream()   // 1:创建流
                //.findFirst()   // 2:操作流
                .map(t->t.getBirthday()).sorted()
                .collect(Collectors.toList())
                ;

        userList.forEach(System.out::println);
    }

}
