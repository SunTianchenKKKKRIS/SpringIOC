import Entity.City;
import Entity.User;
import ioc.Bean;
import ioc.BeanFactory;
import ioc.ClassPathXmlApplicationContext;
import ioc.XmlConfig;
import java.util.Map.Entry;

import java.util.Map;

public class test {
    public static void main(String[] args) {
        testIOC();
        testConfig();
    }

    private static void testIOC(){
        BeanFactory bf = new ClassPathXmlApplicationContext("/resource/ApplicationContext.xml");
        User user = (User) bf.getBean("user");
        System.out.println(user);
        City city = (City) bf.getBean("city");
        System.out.println(city);
    }

    private static void testConfig(){
        Map<String, Bean> map = XmlConfig.getConfig("/resource/ApplicationContext.xml");
        for(Entry<String,Bean> entry:map.entrySet()){
            System.out.println(entry.getKey()+"------"+entry.getValue());
        }
    }
}
