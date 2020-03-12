package ioc;


//此类是模拟Bean的属性类
public class Property {
    private String name;
    private String value;
    private String ref;  //确定值也可以 依赖也可以  都用string了


    //添加了get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
