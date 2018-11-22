public class Main {

    public static void main(String[] args) {
        LoadBalancer loadBalancer = new LoadBalancer();

        for (int i = 0; i < 10; i++) {
            loadBalancer.start();
        }

        System.out.println(loadBalancer.standardDeviation());
        System.out.println(loadBalancer.average());
    }
}
