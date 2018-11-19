import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadBalancer {

    public static Integer QTD_REQUESTS;
    public static Integer QTD_SERVERS;
    public static Integer QTD_PARALLEL_REQUESTS;

    public static List<Request> REQUESTS = new ArrayList<>();
    public static List<Request> REQUESTS_FINISHED = new ArrayList<>();
    public static Long TOTAL_TIME = 0L;

    public LoadBalancer() {

        acceptParameters();

        for (int i = 0; i < QTD_REQUESTS; i++) {
            LoadBalancer.REQUESTS.add(new Request());
        }
    }

    public void start() {

        Thread thread = new Thread(new Processor(), "Processor");
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("---------------- Average Time: " + LoadBalancer.TOTAL_TIME / LoadBalancer.QTD_REQUESTS);
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
}
