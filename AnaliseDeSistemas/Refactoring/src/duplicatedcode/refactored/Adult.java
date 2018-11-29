package duplicatedcode.refactored;

public class Adult extends Person {

    private String hasDriverLicense;

    public String getHasDriverLicense() {
        return hasDriverLicense;
    }

    public void setHasDriverLicense(String hasDriverLicense) {
        this.hasDriverLicense = hasDriverLicense;
    }

    @Override
    public String toString() {
        return super.toString() + " hasDriverLicense: " + hasDriverLicense;
    }
}