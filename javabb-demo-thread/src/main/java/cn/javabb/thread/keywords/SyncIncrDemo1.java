package cn.javabb.thread.keywords;

/**
 * @desc: 修饰静态成员方法
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/09/26 18:42
 */
public class SyncIncrDemo1 implements Runnable {
    // 共享资源（临界资源）
    static int i = 0;

    //锁对象 this (new出来的实例对象)
    public synchronized void reduce() {
        i--;
    }

    //锁对象 class (SyncIncrDemo1.class)
    public static synchronized void incr() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000; j++) {
            incr();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SyncIncrDemo1 syncIncrDemo = new SyncIncrDemo1();
        SyncIncrDemo1 syncIncrDemo1 = new SyncIncrDemo1();
        Thread t1 = new Thread(syncIncrDemo);
        Thread t2 = new Thread(syncIncrDemo1);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i); // 输出结果 <2000
    }
}
