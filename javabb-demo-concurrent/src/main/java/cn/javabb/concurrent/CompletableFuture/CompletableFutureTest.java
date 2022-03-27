package cn.javabb.concurrent.CompletableFuture;

import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * https://blog.csdn.net/qq_31865983/article/details/106137777
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2022/03/26 15:42
 */
public class CompletableFutureTest {

    /**
     * 子线程是异步执行的，主线程休眠等待子线程执行完成，子线程执行完成后唤醒主线程，主线程获取任务执行结果后退出。
     * @throws Exception
     */
    @Test
    public void testFutureSubmit() throws Exception {
        // 创建异步执行任务:
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        Future<Double> cf = executorService.submit(()->{
            System.out.println(Thread.currentThread()+" start,time->"+DateUtil.now());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if(false){
                throw new RuntimeException("test");
            }else{
                System.out.println(Thread.currentThread()+" exit,time->"+DateUtil.now());
                return 1.2;
            }
        });
        System.out.println("main thread start,time->"+DateUtil.now());
        //等待子任务执行完成,如果已完成则直接返回结果
        //如果执行任务异常，则get方法会把之前捕获的异常重新抛出
        System.out.println("run result->"+cf.get());
        System.out.println("main thread exit,time->"+DateUtil.now());
    }

    /**
     * 有返回结果
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        // 创建异步执行任务，有返回值
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread()+" start,time->"+DateUtil.now());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if(false){
                throw new RuntimeException("test");
            }else{
                System.out.println(Thread.currentThread()+" exit,time->"+DateUtil.now());
                return 1.2;
            }
        });
        System.out.println("main thread start,time->"+DateUtil.now());
        //等待子任务执行完成
        System.out.println("run result->"+cf.get());
        System.out.println("main thread exit,time->"+DateUtil.now());
    }

    /**
     * 不返回结果
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        // 创建异步执行任务，无返回值
        CompletableFuture cf = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread()+" start,time->"+DateUtil.now());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if(false){
                throw new RuntimeException("test");
            }else{
                System.out.println(Thread.currentThread()+" exit,time->"+DateUtil.now());
            }
        });
        System.out.println("main thread start,time->"+ DateUtil.now());
        //等待子任务执行完成
        System.out.println("run result->"+cf.get());
        System.out.println("main thread exit,time->"+DateUtil.now());
    }

    /**
     * 返回结果,指定执行线程
     * 可以指定执行异步任务的Executor实现，如果不指定，默认使用ForkJoinPool.commonPool()，
     * 如果机器是单核的，则默认使用ThreadPerTaskExecutor，该类是一个内部类，每次执行execute都会创建一个新线程
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        /**
         * ForkJoinPool pool=new ForkJoinPool();
         * 执行结果
         * Thread[ForkJoinPool-1-worker-9,5,main] start,time->2022-03-27 23:31:28
         * Thread[ForkJoinPool-1-worker-9,5,main] exit,time->2022-03-27 23:31:30
         */
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        /**
         * Thread[pool-1-thread-1,5,main] start,time->2022-03-27 23:33:00
         * Thread[pool-1-thread-1,5,main] exit,time->2022-03-27 23:33:02
         */
        // 创建异步执行任务，有返回值
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread()+" start,time->"+DateUtil.now());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if(false){
                throw new RuntimeException("test");
            }else{
                System.out.println(Thread.currentThread()+" exit,time->"+DateUtil.now());
                return 1.2;
            }
        },executorService);
        System.out.println("main thread start,time->"+DateUtil.now());
        //等待子任务执行完成
        System.out.println("run result->"+cf.get());
        System.out.println("main thread exit,time->"+DateUtil.now());
    }

    /**
     * 异步回调
     * thenApply / thenApplyAsync
     * thenApply 表示某个任务执行完成后执行的动作，即回调方法，会将该任务的执行结果即方法返回值作为入参传递到回调方法中
     *
     */
    @Test
    public void test5() throws Exception {
        ForkJoinPool pool=new ForkJoinPool();
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread()+" start job1,time->"+DateUtil.now());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread()+" exit job1,time->"+DateUtil.now());
            return 1.2;
        },pool);
        //cf关联的异步任务的返回值作为方法入参，传入到thenApply的方法中
        //thenApply这里实际创建了一个新的CompletableFuture实例
        //CompletableFuture<String> cf2=cf.thenApplyAsync((result)->{
        CompletableFuture<String> cf2=cf.thenApply((result)->{
            System.out.println(Thread.currentThread()+" start job2,time->"+DateUtil.now());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread()+" exit job2,time->"+DateUtil.now());
            return "test:"+result;
        });
        System.out.println("main thread start cf.get(),time->"+DateUtil.now());
        //等待子任务执行完成
        System.out.println("run result->"+cf.get());
        System.out.println("main thread start cf2.get(),time->"+DateUtil.now());
        System.out.println("run result->"+cf2.get());
        System.out.println("main thread exit,time->"+DateUtil.now());
    }

    /**
     * thenAccept / thenRun
     * thenAccept 同 thenApply 接收上一个任务的返回值作为参数，但是无返回值；thenRun 的方法没有入参，也买有返回值
     */
    @Test
    public void test6() throws Exception {
        ForkJoinPool pool=new ForkJoinPool();
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread()+" start job1,time->"+DateUtil.now());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread()+" exit job1,time->"+DateUtil.now());
            return 1.2;
        },pool);
        //cf关联的异步任务的返回值作为方法入参，传入到thenApply的方法中
        CompletableFuture cf2=cf.thenApply((result)->{
            System.out.println(Thread.currentThread()+" start job2,time->"+DateUtil.now());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread()+" exit job2,time->"+DateUtil.now());
            return "test:"+result;
        }).thenAccept((result)-> { //接收上一个任务的执行结果作为入参，但是没有返回值
            System.out.println(Thread.currentThread()+" start job3,time->"+DateUtil.now());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(result);
            System.out.println(Thread.currentThread()+" exit job3,time->"+DateUtil.now());
        }).thenRun(()->{ //无入参，也没有返回值
            System.out.println(Thread.currentThread()+" start job4,time->"+DateUtil.now());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println("thenRun do something");
            System.out.println(Thread.currentThread()+" exit job4,time->"+DateUtil.now());
        });
        System.out.println("main thread start cf.get(),time->"+DateUtil.now());
        //等待子任务执行完成
        System.out.println("run result->"+cf.get());
        System.out.println("main thread start cf2.get(),time->"+DateUtil.now());
        //cf2 等待最后一个thenRun执行完成
        System.out.println("run result->"+cf2.get());
        System.out.println("main thread exit,time->"+DateUtil.now());
    }


    /**
     * 多个异步执行
     * allOf / anyOf
     * allOf返回的CompletableFuture是多个任务都执行完成后才会执行，只有有一个任务执行异常，则返回的CompletableFuture执行get方法时会抛出异常，
     * 如果都是正常执行，则get返回null。
     */
    @Test
    public void test11() throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread()+" start job1,time->"+DateUtil.now());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread()+" exit job1,time->"+DateUtil.now());
            return 1.2;
        });
        CompletableFuture<Double> cf2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread()+" start job2,time->"+DateUtil.now());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread()+" exit job2,time->"+DateUtil.now());
            return 3.2;
        });
        CompletableFuture<Double> cf3 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread()+" start job3,time->"+DateUtil.now());
            try {
                Thread.sleep(1300);
            } catch (InterruptedException e) {
            }
//            throw new RuntimeException("test");
            System.out.println(Thread.currentThread()+" exit job3,time->"+DateUtil.now());
            return 2.2;
        });
        //allof等待所有任务执行完成才执行cf4，如果有一个任务异常终止，则cf4.get时会抛出异常，都是正常执行，cf4.get返回null
        //anyOf是只有一个任务执行完成，无论是正常执行或者执行异常，都会执行cf4，cf4.get的结果就是已执行完成的任务的执行结果
        CompletableFuture cf4=CompletableFuture.allOf(cf,cf2,cf3).whenComplete((a,b)->{
            if(b!=null){
                System.out.println("error stack trace->");
                b.printStackTrace();
            }else{
                System.out.println("run succ,result->"+a);
            }
        });

        System.out.println("main thread start cf4.get(),time->"+DateUtil.now());
        //等待子任务执行完成
        System.out.println("cf4 run result->"+cf4.get());
        System.out.println("main thread exit,time->"+DateUtil.now());
    }
}
