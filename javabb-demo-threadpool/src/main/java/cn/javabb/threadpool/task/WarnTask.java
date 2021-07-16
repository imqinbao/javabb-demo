package cn.javabb.threadpool.task;

import lombok.extern.slf4j.Slf4j;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/07/15 19:51
 */
@Slf4j
public class WarnTask extends BaseTask{

    public WarnTask() {
        super("预警");
    }

    @Override
    protected void init() {
        log.info("任务初始化[{}]", this.taskType);
    }

    @Override
    public void run() {
        log.info("[{}]运行中",this.taskType);
    }
}
