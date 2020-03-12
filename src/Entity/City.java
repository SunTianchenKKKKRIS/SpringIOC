package Entity;

public class City {
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + Name + '\'' +
                '}';
    }
}
