package cn.javabb.mq.queue;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/09/27 23:51
 */
public class TestConsumer {

    public static void main(String[] args) {
        Comsumer comsumer = new Comsumer();
        comsumer.init();
        TestConsumer testConsumer = new TestConsumer();
        new Thread(testConsumer.new ConsumerMq(comsumer)).start();
        new Thread(testConsumer.new ConsumerMq(comsumer)).start();
        new Thread(testConsumer.new ConsumerMq(comsumer)).start();
    }
    class ConsumerMq implements Runnable{
        Comsumer comsumer;
        public ConsumerMq(Comsumer comsumer){
            this.comsumer = comsumer;
        }

        @Override
        public void run() {
            while(true){
                try {
                    comsumer.getMessage("javabb-demo-activemq");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
