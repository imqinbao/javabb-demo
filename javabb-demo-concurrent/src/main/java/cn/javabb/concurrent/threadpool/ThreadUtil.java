package cn.javabb.concurrent.threadpool;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2022/03/30 23:50
 */
public class ThreadUtil {

    public ThreadUtil() {
    }

    /**
     * 默认创建
     * @param nThreads
     * @return
     */
    public static ThreadPoolTaskExecutor newThreadPool(Integer nThreads) {
        return (new ThreadPoolCreate()).getTaskExecutor(nThreads);
    }

    /**
     * 自定义创建
     * @param properties
     * @param nThreads
     * @return
     */
    public static ThreadPoolTaskExecutor newThreadPool(ThreadPoolCreate.ThreadPoolProperties properties, Integer nThreads) {
        ThreadPoolTaskExecutor taskExecutor = null;

        try {
            taskExecutor = (new ThreadPoolCreate(properties)).getTaskExecutor(nThreads);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taskExecutor;
    }

}
