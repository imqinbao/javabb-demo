package cn.javabb.sharding.config;

import cn.hutool.core.lang.Snowflake;
import io.shardingjdbc.core.keygen.KeyGenerator;

/**
 * @desc: 自定义雪花算法，替换 DefaultKeyGenerator，避免DefaultKeyGenerator生成的id大几率是偶数
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/07/01 17:37
 */
public class CustomSnowflakeKeyGenerator implements KeyGenerator {

    private Snowflake snowflake;

    public CustomSnowflakeKeyGenerator(Snowflake snowflake) {
        this.snowflake = snowflake;
    }

    @Override
    public Number generateKey() {
        return snowflake.nextId();
    }
}
