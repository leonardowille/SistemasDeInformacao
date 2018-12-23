public class Barber extends Thread {

    public Barber(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            Client client = selectClient();
            if (client != null) {
                cutHair(client);
            } else {
                System.out.println(this.getName() + " Dormindo");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Client selectClient() {
        synchronized (BarberShop.queue) {
            if (BarberShop.queue.size() > 0) {
                return BarberShop.queue.remove(0);
            }
            return null;
        }
    }

    private void cutHair(Client client) {
        System.out.println(this.getName() + " cortando o cabelo do " + client.getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getName() + " terminou o cabelo do " + client.getName());
    }
}
