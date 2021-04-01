package cn.javabb.thread;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/09/16 23:52
 */
public class ThreadDemo2 {

    public static void main(String[] args) {
        CreateThread2 createThread2 = new CreateThread2();
        Thread thread = new Thread(createThread2);
        thread.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("main--" + i);
        }
    }
}

class CreateThread2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("CreateThread2--" + i);
        }
    }
}