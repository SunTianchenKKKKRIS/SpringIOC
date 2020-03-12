package ioc;

import java.util.ArrayList;

public class Bean {
    private String id;
    private String classname;

    private ArrayList<Property> properties = new ArrayList<Property>();//一个bean有多个属性


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "id='" + id + '\'' +
                ", classname='" + classname + '\'' +
                ", properties=" + properties +
                '}';
    }
}
