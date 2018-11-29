package duplicatedcode.badsmell;

public class Kid extends Person {

    private Long id;
    private String daycare;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDaycare() {
        return daycare;
    }

    public void setDaycare(String daycare) {
        this.daycare = daycare;
    }

    @Override
    public String toString() {
        return "id: " + id + " name: " + getName() + " daycare: " + daycare;
    }
}