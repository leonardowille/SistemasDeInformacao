package duplicatedcode.badsmell;

public class Adult extends Person {

    private Long id;
    private String hasDriverLicense;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHasDriverLicense() {
        return hasDriverLicense;
    }

    public void setHasDriverLicense(String hasDriverLicense) {
        this.hasDriverLicense = hasDriverLicense;
    }

    @Override
    public String toString() {
        return "id: " + id + " name: " + getName() + " hasDriverLicense: " + hasDriverLicense;
    }
}