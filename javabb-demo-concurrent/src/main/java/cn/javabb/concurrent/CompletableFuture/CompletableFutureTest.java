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
     * thenApply / thenApplyAsync  表示某个任务执行完成后执行的动作，即回调方法，会将该任务的执行结果即方法返回值作为入参传递到回调方法中
     * thenApply 入参有返回值 ,  表示同一线程处理
     * thenApplyAsync 入参有返回值, 表示不同线程处理,也可以指定线程
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
        CompletableFuture<String> cf2=cf.thenApplyAsync((result)->{
            System.out.println(Thread.currentThread()+" start job2,time->"+DateUtil.now());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread()+" exit job2,time->"+DateUtil.now());
            return "test:"+result;
        },pool);
        /**
         * job1执行结束后，将job1的方法返回值作为入参传递到job2中并立即执行job2。
         * thenApplyAsync与thenApply的区别在于，前者是将job2提交到线程池中异步执行，
         * 实际执行job2的线程可能是另外一个线程，后者是由执行job1的线程立即执行job2，即两个job都是同一个线程执行的
         */
        System.out.println("main thread start cf.get(),time->"+DateUtil.now());
        //等待子任务执行完成
        System.out.println("run result->"+cf.get());
        System.out.println("main thread start cf2.get(),time->"+DateUtil.now());
        System.out.println("run result->"+cf2.get());
        System.out.println("main thread exit,time->"+DateUtil.now());
    }

    /**
     * thenAccept / thenRun
     * thenAccept 同 thenApply 接收上一个任务的返回值作为参数，但是无返回值；
     * thenRun 的方法没有入参，也买有返回值
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
     * 异步执行 正常/异常处理
     * @throws Exception
     */
    @Test
    public void test15() throws Exception {
        ForkJoinPool pool=new ForkJoinPool();
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread()+"job1 start,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if(true){
                throw new RuntimeException("test");
            }else{
                System.out.println(Thread.currentThread()+"job1 exit,time->"+System.currentTimeMillis());
                return 1.2;
            }
        },pool);
        //cf执行异常时，将抛出的异常作为入参传递给回调方法
        CompletableFuture<Double> cf2= cf.exceptionally((param)->{
            System.out.println(Thread.currentThread()+" start,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println("error stack trace->");
            param.printStackTrace();
            System.out.println(Thread.currentThread()+" exit,time->"+System.currentTimeMillis());
            return -1.1;
        });
        //cf正常执行时执行的逻辑，如果执行异常则不调用此逻辑
        CompletableFuture cf3=cf.thenAccept((param)->{
            System.out.println(Thread.currentThread()+"job2 start,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println("param->"+param);
            System.out.println(Thread.currentThread()+"job2 exit,time->"+System.currentTimeMillis());
        });
        System.out.println("main thread start,time->"+System.currentTimeMillis());
        //等待子任务执行完成,此处无论是job2和job3都可以实现job2退出，主线程才退出，如果是cf，则主线程不会等待job2执行完成自动退出了
        //cf2.get时，没有异常，但是依然有返回值，就是cf的返回值
        System.out.println("run result->"+cf2.get());
        System.out.println("main thread exit,time->"+System.currentTimeMillis());
    }

    /**
     * whenComplete(result,exception) 相当于thenAccept+exceptionally
     * @throws Exception
     */
    @Test
    public void test10() throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread()+"job1 start,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if(false){
                throw new RuntimeException("test");
            }else{
                System.out.println(Thread.currentThread()+"job1 exit,time->"+System.currentTimeMillis());
                return 1.2;
            }
        });
        //cf执行完成后会将执行结果和执行过程中抛出的异常传入回调方法，如果是正常执行的则传入的异常为null
        CompletableFuture<Double> cf2=cf.whenComplete((a,b)->{
            System.out.println(Thread.currentThread()+"job2 start,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if(b!=null){
                System.out.println("error stack trace->");
                b.printStackTrace();
            }else{
                System.out.println("run succ,result->"+a);
            }
            System.out.println(Thread.currentThread()+"job2 exit,time->"+System.currentTimeMillis());
        });
        //等待子任务执行完成
        System.out.println("main thread start wait,time->"+System.currentTimeMillis());
        //如果cf是正常执行的，cf2.get的结果就是cf执行的结果
        //如果cf是执行异常，则cf2.get会抛出异常
        System.out.println("run result->"+cf2.get());
        System.out.println("main thread exit,time->"+System.currentTimeMillis());
    }

    /**
     * handle
     * 类似于whenComplete,但是handle有返回值
     * @throws Exception
     */
    @Test
    public void test14() throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread()+"job1 start,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if(true){
                throw new RuntimeException("test");
            }else{
                System.out.println(Thread.currentThread()+"job1 exit,time->"+System.currentTimeMillis());
                return 1.2;
            }
        });
        //cf执行完成后会将执行结果和执行过程中抛出的异常传入回调方法，如果是正常执行的则传入的异常为null
        CompletableFuture<String> cf2=cf.handle((a,b)->{
            System.out.println(Thread.currentThread()+"job2 start,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if(b!=null){
                System.out.println("error stack trace->");
                b.printStackTrace();
            }else{
                System.out.println("run succ,result->"+a);
            }
            System.out.println(Thread.currentThread()+"job2 exit,time->"+System.currentTimeMillis());
            if(b!=null){
                return "run error";
            }else{
                return "run succ";
            }
        });
        //等待子任务执行完成
        System.out.println("main thread start wait,time->"+System.currentTimeMillis());
        //get的结果是cf2的返回值，跟cf没关系了
        System.out.println("run result->"+cf2.get());
        System.out.println("main thread exit,time->"+System.currentTimeMillis());
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


    /**
     * thenCombine / thenAcceptBoth / runAfterBoth
     *  这三个方法都是将两个CompletableFuture组合起来，只有这两个都正常执行完了才会执行某个任务，区别在于，
     *  thenCombine会将两个任务的执行结果作为方法入参传递到指定方法中，且该方法有返回值；
     *  thenAcceptBoth同样将两个任务的执行结果作为方法入参，但是无返回值；
     *  runAfterBoth没有入参，也没有返回值。注意两个任务中只要有一个执行异常，则将该异常信息作为指定任务的执行结果。
     */


    /**
     * applyToEither / acceptEither / runAfterEither
     *  这三个方法都是将两个CompletableFuture组合起来，只要其中一个执行完了就会执行某个任务，其区别在于
     *  applyToEither会将已经执行完成的任务的执行结果作为方法入参，并有返回值；
     *  acceptEither同样将已经执行完成的任务的执行结果作为方法入参，但是没有返回值；
     *  runAfterEither没有方法入参，也没有返回值。注意两个任务中只要有一个执行异常，则将该异常信息作为指定任务的执行结果
     */
}
