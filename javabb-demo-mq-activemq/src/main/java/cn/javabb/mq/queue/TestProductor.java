package cn.javabb.mq.queue;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/09/15 19:00
 */
public class TestProductor {

    public static void main(String[] args) {
        Producter producter = new Producter();
        producter.init();
        TestProductor testProductor = new TestProductor();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //new Thread()
        new Thread(testProductor.new ProductorMq(producter)).start();
        new Thread(testProductor.new ProductorMq(producter)).start();
        new Thread(testProductor.new ProductorMq(producter)).start();
    }

    class ProductorMq implements Runnable {

        Producter producter;

        public ProductorMq(Producter producter) {
            this.producter = producter;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    producter.sendMessage("javabb-demo-activemq");
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
