package cn.javabb.mix;

import cn.javabb.mix.handler.Factory;
import cn.javabb.mix.handler.Handler;
import cn.javabb.mix.handler.ZhangsanHandler;
import org.junit.Test;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2022/10/27 23:52
 */
public class StrategyFatoryTest {

    /**
     * no design
     */
    @Test
    public void test1() {
        String name = "zhangsan";

        if (name.equals("zhangsan")) {
            System.out.println("zhangsan finish task.");
        }else if (name.equals("lisi")) {
            System.out.println("lisi finish task.");
        }else if (name.equals("zhaowu")) {
            System.out.println("zhaowu finish task.");
        }


    }

    /**
     * by design
     */
    @Test
    public void test2() {
        String name = "lisi";
        if (name.equals("zhangsan")) {
            //System.out.println("zhangsan finish task.");

            new ZhangsanHandler().AAA(name);

        }else if (name.equals("lisi")) {
            // System.out.println("lisi finish task.");
            Handler lisi = Factory.getInvokeStrategy(name);
            lisi.AAA(name);
        }else if (name.equals("zhaowu")) {
            System.out.println("zhaowu finish task.");
        }

    }

}
