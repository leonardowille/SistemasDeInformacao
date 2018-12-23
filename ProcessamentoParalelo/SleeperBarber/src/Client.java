public class Client extends Thread {

    public Client(String name) {
        super(name);
    }

    private synchronized void addQueue() {
        if (BarberShop.queue.size() < BarberShop.CHAIRS) {
            BarberShop.queue.add(this);
        } else {
            System.out.println(getName() + " não foi atendido");
        }
    }

    @Override
    public void run() {
        System.out.println("Chegou o " + getName());
        addQueue();
    }
}