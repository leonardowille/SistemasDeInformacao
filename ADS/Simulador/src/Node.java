public class Node implements Runnable {

    private final Integer DELAY_SERVICE = 1000;

    private final Integer LATENCIA = 300;

    @Override
    public void run() {

        while (LoadBalancer.REQUESTS.size() > 0) {
            try {
                Request reqFinished = LoadBalancer.REQUESTS.remove(0);

                if (reqFinished != null) {
                    LoadBalancer.REQUESTS_FINISHED.add(reqFinished);
                    try {
                        Thread.sleep(getTimeToSleep());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    reqFinished.finishRequest();
                    LoadBalancer.TOTAL_TIME += reqFinished.getTotalTime();
                }

            } catch (IndexOutOfBoundsException e) {
            }
        }
    }

    private Integer getTimeToSleep() {
        return DELAY_SERVICE + LATENCIA;
    }
}
