package cn.javabb.threadpool.task;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/07/15 18:59
 */
@Slf4j
public class SmsTask extends BaseTask{

    public SmsTask() {
        super("短信");
    }

    @Override
    public void init() {
        log.info("任务初始化[{}]", this.taskType);
    }

    @Override
    public void run() {
        log.info("[{}][{}]运行中", DateUtil.format(this.submitDate,"yyyy-MM-dd HH:mm:ss"),this.taskType);
        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }
    }
}
