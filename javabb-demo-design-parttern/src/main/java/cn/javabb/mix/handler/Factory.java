package cn.javabb.mix.handler;

import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2022/10/27 23:39
 */
public class Factory {

    private static Map<String, Handler> strategyMap = new HashMap<>();

    public static Handler getInvokeStrategy(String str) {
        return strategyMap.get(str);
    }


    public static void register(String str, Handler handler) {
        if (StrUtil.isEmpty(str) || null == handler) {
            return;
        }

        strategyMap.put(str, handler);
    }

}
