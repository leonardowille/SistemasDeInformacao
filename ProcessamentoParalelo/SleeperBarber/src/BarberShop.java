import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BarberShop {

    private static int counter = 1;

    private int quantityBarber;

    static List<Client> queue = new ArrayList<>();
    static final int CHAIRS = 5;

    public BarberShop(int quantityBarber) {
        this.quantityBarber = quantityBarber;
    }

    public void start() {

        for (int i = 1; i <= this.quantityBarber; i++) {
            new Barber("Barbeiro " + i).start();
        }

        while (true) {
            new Client("Cliente " + counter++).start();

            try {
                Random random = new Random();
                Thread.sleep((random.nextInt(4) + 1) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
