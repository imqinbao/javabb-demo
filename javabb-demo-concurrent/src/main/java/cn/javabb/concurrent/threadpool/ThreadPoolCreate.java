package cn.javabb.concurrent.threadpool;

import cn.hutool.core.lang.Assert;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2022/03/30 23:31
 */
public class ThreadPoolCreate {

    private ThreadPoolCreate.ThreadPoolProperties properties;

    public ThreadPoolCreate() {
        this.properties = new ThreadPoolCreate.ThreadPoolProperties();
        this.properties.setCorePoolSize(20);
        this.properties.setMaxPoolSize(20);
        this.properties.setQueneCapacity(65535);
        this.properties.setWaitForTaskToCompleteOnShutdown(true);
        this.properties.setAwaitTerminationSeconds(60);
    }

    public ThreadPoolCreate(ThreadPoolCreate.ThreadPoolProperties properties) {
        Assert.notNull(properties,"threadPoolProperties参数设置错误");
        Assert.notNull(properties.getCorePoolSize(),"corePoolSize 不能为空");
        Assert.notNull(properties.getMaxPoolSize(),"maxPoolSize 不能为空");
        Assert.notNull(properties.getQueneCapacity(),"queneCapacity 不能为空");
        Assert.notNull(properties.getWaitForTaskToCompleteOnShutdown(),"waitForTaskToCompleteOnShutdown 不能为空");
        Assert.notNull(properties.getAwaitTerminationSeconds(),"awaitTerminationSeconds 不能为空");
        this.properties = properties;
    }

    public void setProperties(ThreadPoolCreate.ThreadPoolProperties properties) {
        this.properties = properties;
    }

    public ThreadPoolTaskExecutor getTaskExecutor(Integer nThreads) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor(){
            private static final long serialVersionUID = 1L;
            // 重写线程执行
            @Override
            public void execute(Runnable task) {
                super.execute(task);
            }

            @Override
            public void execute(Runnable task, long startTimeout) {
                super.execute(task, startTimeout);
            }

            @Override
            public Future<?> submit(Runnable task) {
                return super.submit(task);
            }

            @Override
            public <T> Future<T> submit(Callable<T> task) {
                return super.submit(task);
            }

            @Override
            public ListenableFuture<?> submitListenable(Runnable task) {
                return super.submitListenable(task);
            }

            @Override
            public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
                return super.submitListenable(task);
            }

            @Override
            protected void cancelRemainingTask(Runnable task) {
                super.cancelRemainingTask(task);
            }
        };
        this.setProperties(nThreads,executor);
        executor.initialize();
        return executor;
    }


    private void setProperties(Integer nThreads, ThreadPoolTaskExecutor executor) {
        if (null == nThreads) {
            executor.setCorePoolSize(this.properties.getCorePoolSize());
            executor.setMaxPoolSize(this.properties.getMaxPoolSize());
        }else{
            executor.setCorePoolSize(nThreads);
            executor.setMaxPoolSize(nThreads);
        }
        executor.setQueueCapacity(this.properties.getQueneCapacity());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(this.properties.getWaitForTaskToCompleteOnShutdown());
        executor.setAwaitTerminationSeconds(this.properties.getAwaitTerminationSeconds());
    }

    public static class ThreadPoolProperties {
        // 线程池维护线程的最少数量
        private Integer corePoolSize;
        // 线程池维护线程的最大数量,只有在缓冲队列满了之后才会申请超过核心线程数的线程
        private Integer maxPoolSize;
        // 缓存队列
        private Integer queneCapacity;
        // 调度器shutdown被调用时等待当前被调度的任务完成
        private Boolean waitForTaskToCompleteOnShutdown;
        // 等待时长
        private Integer awaitTerminationSeconds;

        public ThreadPoolProperties() {
        }

        public Integer getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(Integer corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public Integer getMaxPoolSize() {
            return maxPoolSize;
        }

        public void setMaxPoolSize(Integer maxPoolSize) {
            this.maxPoolSize = maxPoolSize;
        }

        public Integer getQueneCapacity() {
            return queneCapacity;
        }

        public void setQueneCapacity(Integer queneCapacity) {
            this.queneCapacity = queneCapacity;
        }

        public Boolean getWaitForTaskToCompleteOnShutdown() {
            return waitForTaskToCompleteOnShutdown;
        }

        public void setWaitForTaskToCompleteOnShutdown(Boolean waitForTaskToCompleteOnShutdown) {
            this.waitForTaskToCompleteOnShutdown = waitForTaskToCompleteOnShutdown;
        }

        public Integer getAwaitTerminationSeconds() {
            return awaitTerminationSeconds;
        }

        public void setAwaitTerminationSeconds(Integer awaitTerminationSeconds) {
            this.awaitTerminationSeconds = awaitTerminationSeconds;
        }
    }
}
