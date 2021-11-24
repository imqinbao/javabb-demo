package cn.javabb.test.jdk8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/01/05 21:02
 */
public class StreamTest {

    public List<String> newList() {
        return Arrays.asList("a1", "b2", "c1", "c2", "d5");
    }

    // 试试水
    @Test
    public void test1() {
        newList().stream().filter(s -> s.startsWith("c")) // 数据过滤
                .map(String::toUpperCase) //转换大小写
                .sorted() //排序
                .forEach(System.out::println); // for 循环打印 终端操作,流的结束操作,void类型
    }

    @Test
    public void test2() {
        // 流支持的类型主要是Collection  eg:List、Set
        // 找到第一个流
        newList().stream().findFirst().ifPresent(System.out::println);
        // Stream.of()直接创建流
        Stream.of("a1", "a2", "b3").findFirst().ifPresent(System.out::println);

        // 其他原始数据类型的流 IntStream,LongSteam,DoubleStream
        IntStream.of(1, 2, 3).forEach(System.out::println);
        //上面的等同于  下面range的用法相当于 for(int i=1;i<4;i++){}
        IntStream.range(1, 4).forEach(System.out::println);
    }

}
