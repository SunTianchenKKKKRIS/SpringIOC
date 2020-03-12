package Entity;

public class Sex {
    private String Sex;

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    @Override
    public String toString() {
        return "Sex{" +
                "Sex='" + Sex + '\'' +
                '}';
    }
}
