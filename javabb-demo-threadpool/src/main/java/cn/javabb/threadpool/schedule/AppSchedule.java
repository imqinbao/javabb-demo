package cn.javabb.threadpool.schedule;

import cn.javabb.threadpool.task.BaseTask;
import cn.javabb.threadpool.task.SmsTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.Executor;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/07/15 18:45
 */
@Component
public class AppSchedule {

    @Autowired
    Executor executor;

    @Scheduled(cron = "0/2 * * * * ?")
    public void schedule() {
        for (int i = 0; i < 50; i++) {
            BaseTask task = new SmsTask();
            task.submitDate = new Date();
            executor.execute(task);
        }
    }

}
