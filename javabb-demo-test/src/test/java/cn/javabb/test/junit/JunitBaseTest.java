package cn.javabb.test.junit;

import org.junit.*;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/11/24 23:11
 */
public class JunitBaseTest {

    /**
     * 通过加@Test注解表示这是个测试方法，可以直接执行
     * 测试方法必须要满足下面三点：
     * 1，方法修饰一定要是public
     * 2，方法返回一定要是void
     * 3，方法不能有参数
     */
    @Test
    public void test1() {
        System.out.println("---hello world!---");
    }

    /**
     * 方法针对每一个测试用例执行，但是是在执行测试用例之前。
     */
    @Before
    public void before() {
        System.out.println("---before---");
    }

    /**
     * 方法针对每一个测试用例执行，但是是在执行测试用例之后。
     */
    @After
    public void after() {
        System.out.println("---after---");
    }
    /**
     * 方法执行前执行，只执行一次
     */
    @BeforeClass
    public void beforeClass() {
        System.out.println("---before-class---");
    }

    /**
     * 方法执行后执行，只执行一次
     */
    @AfterClass
    public void afterClass() {
        System.out.println("---after-class---");
    }
}
