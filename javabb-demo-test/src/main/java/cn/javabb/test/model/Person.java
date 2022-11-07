package cn.javabb.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/09/03 00:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String id;

    private String name;
    /**
     * 1:男; 2:女; 3:未知
     */
    private Integer sex;
    private Double height;
    /**
     * 地址
     */
    private String location;

    private BigDecimal salary;

    public Person(String id, String name, Integer sex, Double height) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.height = height;
    }
}
