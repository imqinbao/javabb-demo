package cn.javabb.test.junit;

import org.junit.Assert;
import org.junit.Test;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/11/24 23:14
 */
public class JunitAssertTest {

    @Test
    public void test1() {
        // 判断条件为真
        Assert.assertTrue(true);
        // 判断条件为真，不为真返回错误message
        Assert.assertTrue("错误", true);
        // 与assertFalse相反
        Assert.assertFalse(false);
        // 判断条件为空，只能为null，为“”，0，“ ”都不行
        Assert.assertNull(null);
        // 判断两个条件是否相等
        Assert.assertEquals("javabb","javabb");
        // 判断两个参数指向的对象是否一样
        String a = "javabb";
        String b = "javabb";
        String c = new String("javabb");
        Assert.assertSame(a, b);
        Assert.assertNotSame(a,c);
        // 判断两个数组是否相等，下面的如果换了元素的位置就不相等了。
        Assert.assertArrayEquals(new int[]{1,2,3},new int[]{1,2,3});
        //Assert.assertArrayEquals(new int[]{1,2,3},new int[]{3,2,1});
    }

}
