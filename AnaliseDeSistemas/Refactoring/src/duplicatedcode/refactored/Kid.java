package duplicatedcode.refactored;

public class Kid extends Person {

    private String daycare;

    public String getDaycare() {
        return daycare;
    }

    public void setDaycare(String daycare) {
        this.daycare = daycare;
    }

    @Override
    public String toString() {
        return super.toString() + " daycare: " + daycare;
    }
}