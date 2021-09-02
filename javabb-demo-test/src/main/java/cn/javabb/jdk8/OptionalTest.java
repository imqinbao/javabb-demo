package cn.javabb.jdk8;

import cn.javabb.model.Person;
import org.junit.Test;

import java.util.Optional;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/09/03 00:21
 */
public class OptionalTest {

    @Test
    public void test1() {
        Optional<Person> emptyOpt = Optional.empty();
        System.out.println(emptyOpt.isPresent());
    }
}
