package cn.javabb.test.model;

import lombok.Data;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/09/03 00:23
 */
@Data
public class Person {
    int id;
    String name;
    String sex;
    float height;

    public Person(int id, String name, String sex, float height) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.height = height;
    }
}
