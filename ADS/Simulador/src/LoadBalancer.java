import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadBalancer {

    private List<Long> averages = new ArrayList<>();

    public static Integer QTD_REQUESTS;
    public static Integer QTD_SERVERS;
    public static Integer QTD_PARALLEL_REQUESTS;

    public static List<Request> REQUESTS;
    public static List<Request> REQUESTS_FINISHED;
    public static Long TOTAL_TIME;

    public LoadBalancer() {
        acceptParameters();
    }

    public void start() {

        resetLoadBalancer();

        for (int i = 0; i < QTD_REQUESTS; i++) {
            LoadBalancer.REQUESTS.add(new Request());
        }

        Thread thread = new Thread(new Processor(), "Processor");
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Long average = LoadBalancer.TOTAL_TIME / LoadBalancer.QTD_REQUESTS;
        averages.add(average);
        System.out.println("Average: " + average);
    }

    private void acceptParameters() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Quantidade de requisições:");
        LoadBalancer.QTD_REQUESTS = sc.nextInt();

        System.out.println("Quantidade de servidores:");
        LoadBalancer.QTD_SERVERS = sc.nextInt();

        System.out.println("Quantidade de requisições pararelas que cada servidor suporta:");
        LoadBalancer.QTD_PARALLEL_REQUESTS = sc.nextInt();
    }

    private void resetLoadBalancer() {
        REQUESTS = new ArrayList<>();
        REQUESTS_FINISHED = new ArrayList<>();
        TOTAL_TIME = 0L;
    }

    public double standardDeviation() {
        if (this.averages.size() == 1) {
            return 0.0;
        } else {
            double average = average();
            double sum = 0l;
            for (int i = 0; i < this.averages.size(); i++) {
                double result = this.averages.get(i) - average;
                sum = sum + result * result;
            }
            return Math.sqrt(((double) 1 /( this.averages.size()-1))
                    * sum);
        }
    }

    public double average() {
        double sum = 0l;
        for (Long d : this.averages) {
            sum += d;
        }
        return sum / this.averages.size();
    }
}
