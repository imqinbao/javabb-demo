package cn.javabb.thread.keywords;

/**
 * @desc: 修饰代码块
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/09/26 18:42
 */
public class SyncIncrDemo2 implements Runnable {
    // 共享资源（临界资源）
    static int i = 0;

    public void method1() {
        //省略1w行代码
        synchronized (SyncIncrDemo2.class) {
            i++;
        }
        //省略2w行代码
    }

    @Override
    public void run() {
        method1();
    }

    public static void main(String[] args) throws InterruptedException {
        SyncIncrDemo2 syncIncrDemo = new SyncIncrDemo2();
        for (int j = 0; j < 1000; j++) {
            new Thread(syncIncrDemo).start();
        }
        Thread.sleep(10000);
        System.out.println(i); // 输出结果 1000
    }
}
