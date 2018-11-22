import java.util.ArrayList;
import java.util.List;

public class Processor implements Runnable {

    @Override
    public void run() {
        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= LoadBalancer.QTD_SERVERS; i++) {
            threads.add(new Thread(new Server(), "Server " + i));
        }

        for (Thread t: threads) {
            t.start();
        }

        for (Thread t: threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
