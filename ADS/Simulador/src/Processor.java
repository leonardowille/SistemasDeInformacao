public class Processor implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < LoadBalancer.QTD_SERVERS; i++) {
            new Thread(new Server()).start();
        }
    }
}
