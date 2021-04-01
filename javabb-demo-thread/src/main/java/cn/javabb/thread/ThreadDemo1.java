package cn.javabb.thread;

/**
 * 创建一个继承于Thread类的子类
 * 重写Thread类中的run()：将此线程要执行的操作声明在run()
 * 创建Thread的子类的对象
 * 调用此对象的start():启动线程 调用当前线程的run()方法
 * 注意：线程只能启动一次，不能再次调用，就是再调用线程的run方法也只有一个线程
 *
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/09/16 22:32
 */
public class ThreadDemo1 {

    public static void main(String[] args) {
        CreateThread1 thread1 = new CreateThread1();
        thread1.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("main--" + i);
        }
    }

}

class CreateThread1 extends Thread {
    /**
     * 开启线程的几种方式
     * 1，继承Thread，实现Thread的run方法
     */
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("CreateThread1--" + i);
        }
    }

}