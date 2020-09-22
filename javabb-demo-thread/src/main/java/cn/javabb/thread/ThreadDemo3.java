package cn.javabb.thread;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/09/17 23:05
 */
public class ThreadDemo3 {

    public static void main(String[] args) {
        /**
         * 线程调度与控制
         *
         * 1. 给线程取名,如果不取名字就是默认的名字 Thread-0,1,2这样
         * 2. 给线程设置优先级setPriority,0-10,越大优先级越高
         */
        Thread t1 = new Thread(new Processer());
        t1.setName("t1"); //给线程取名
        t1.setPriority(2); // 设置线程优先级

        Thread t2 = new Thread(new Processer());
        t2.setName("t2");
        t2.setPriority(10);

        t1.start(); // 告诉JVM再分配一个新的栈给t线程，run不需程序员手动调用
        t2.start(); // 系统线程启动后自动调用run方法

        /**
         * 线程阻塞sleep与终止interrupt
         * Thread.sleep(ms)，是一个静态方法，阻塞当前线程，腾出CPU，让给其他线程
         */
        Thread thread = new Thread(new Processer2());
    }
}

class Processer implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1; i++) {
            System.out.println(Thread.currentThread().getName() +"--"+ i);
        }
    }
}
class ThreadDemo3_1{

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Processer2());
        t.setName("t3");
        t.start();
        Thread.sleep(3000);
        t.interrupt(); // 打断t的休眠
    }
}
class Processer2 implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "-" + i);
        }
    }
}