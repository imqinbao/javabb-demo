package cn.javabb.threadpool.task;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/07/15 18:52
 */
@Slf4j
public abstract class BaseTask implements Runnable{

    protected String taskType;
    public Date submitDate;

    public BaseTask(String taskType) {
        this.taskType = taskType;
    }

    protected abstract void init();
}
