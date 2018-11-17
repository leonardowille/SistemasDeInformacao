public class Node implements Runnable {

    @Override
    public void run() {
        System.out.println("START: " + Thread.currentThread().getName());

        while (LoadBalancer.REQUESTS.size() > 0) {

            try {

                Request reqFinished = LoadBalancer.REQUESTS.remove(0);

                if (reqFinished != null){
                    LoadBalancer.REQUESTS_FINISHED.add(reqFinished);
//            System.out.println("Pendentes: " + LoadBalancer.REQUESTS.size() + " - Concluídos: " + LoadBalancer.REQUESTS_FINISHED.size());

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    reqFinished.finishRequest();
                    LoadBalancer.TOTAL_TIME += reqFinished.getTotalTime();
                }

            } catch (IndexOutOfBoundsException e){
            }
        }
        System.out.println("END: " + Thread.currentThread().getName());
    }
}
