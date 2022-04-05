package cn.javabb.task.xxljob.annotation;

import java.lang.annotation.*;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2022/04/04 00:46
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface XxlJobAutoRegistry {

    String jobGroupName();

    /**
     * 调度类型  CRON 和 FIX_RATE
     * @return
     */
    String scheduleType();

    /**
     * 调度配置，值含义取决于调度类型
     * @return
     */
    String scheduleConf();

    String jobDesc();

    String author();

    String alarmEmail();

    String executorRouteStrategy() default "FAILOVER";

    String executorHandler();

    String executorBlockStrategy() default "SERIAL_EXECUTION";

    int executorTimeout() default 0;

    int executorFailRetryCount() default 0;

    String glueType() default "BEAN";
}
