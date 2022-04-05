package cn.javabb.task.xxljob.job;

import cn.hutool.core.date.DateUtil;
import cn.javabb.task.xxljob.annotation.XxlJobAutoRegistry;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2022/04/05 15:12
 */
@Component
@Slf4j
public class TestJob {

    @XxlJob("test1Task")
    @XxlJobAutoRegistry(
            scheduleType = "CRON",
            scheduleConf = "0 0/1 * * * ? *",
            jobDesc = "测试自动注册定时任务",
            author = "javabb",
            alarmEmail = "javabob@163.com",
            executorHandler = "test1Task",
            jobGroupName = "xxl-job-executor-test"
    )
    public ReturnT<String> test1Task(String param) {

        log.info("xxl-job test1 start , startTime:{}", DateUtil.now());

        return ReturnT.SUCCESS;
    }
}
