import java.util.ArrayList;
import java.util.List;

public class LoadBalancer {

    public static Integer QTD_REQUETS = 5000;
    public static Integer QTD_SERVERS = 5;
    public static Integer QTD_PARALLEL_REQUESTS = 1000;

    public static List<Request> REQUESTS = new ArrayList<>();
    public static List<Request> REQUESTS_FINISHED = new ArrayList<>();
    public static Long TOTAL_TIME = 0L;

    public LoadBalancer() {
        for (int i = 0; i < QTD_REQUETS; i++) {
            LoadBalancer.REQUESTS.add(new Request());
        }
    }

    public void start() {
        new Thread(new Processor()).start();
    }
}
