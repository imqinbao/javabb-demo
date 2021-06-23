package cn.javabb.mq.activemq.p2pMult;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/06/22 20:24
 */
public class TestMultProducter {
    public static void main(String[] args) {
        Producter producter = new Producter();
        producter.init();
        TestMultProducter testProductor = new TestMultProducter();
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
