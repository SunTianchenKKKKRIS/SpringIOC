package Entity;

public class User {
    private String Name;
    private City city;
    private Sex sex;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setSex(Sex sex){this.sex = sex;}

    @Override
    public String toString() {
        return "User{" +
                "Name='" + Name + '\'' +
                ", city=" + city +
                ", sex=" + sex +
                '}';
    }
}
