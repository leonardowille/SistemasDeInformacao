public class Nodo implements Runnable {

    @Override
    public void run() {
        while (LoadBalancer.REQUESTS.size() > 0) {
            Request reqFinished = LoadBalancer.REQUESTS.remove(0);
            LoadBalancer.REQUESTS_FINISHED.add(reqFinished);
            System.out.println("Pendentes: " + LoadBalancer.REQUESTS.size() + " - Concluídos: " + LoadBalancer.REQUESTS_FINISHED.size());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            reqFinished.finishRequest();
            LoadBalancer.TOTAL_TIME += reqFinished.getTotalTime();
        }

        System.out.println("Average Time: " + LoadBalancer.TOTAL_TIME / LoadBalancer.QTD_REQUETS);
    }

}
