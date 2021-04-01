package cn.javabb.thread;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/09/26 17:49
 */
public class ThreadDemo3 {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() + "-" + i);
                }
            }
        });
        thread.start();

    }
}
