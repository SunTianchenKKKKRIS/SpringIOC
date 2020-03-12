package ioc;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class XmlConfig {

    public static Map<String, Bean> getConfig(String path) {
        Map<String, Bean> configMap = new HashMap<String, Bean>();
        //使用dom4j和Xpath读取xml
        Document doc = null;
        SAXReader reader = new SAXReader();
        InputStream in = XmlConfig.class.getResourceAsStream(path);
        try {
            doc = reader.read(in);
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("xml配置文件路径错误");
        }

        //定义xpath 取出所有bean
        String xpath = "//bean";
        //遍历Bean

        System.out.println("结果="+doc.selectNodes(xpath).get(0));
        List<Node> list =  doc.selectNodes(xpath);  //集合存储从xpath中取出的所有bean
        if (list != null) {
            for (Node node : list) {
                //把取出来的bean每一个id 和 class 拿出来
                Element beanEle = (Element) node;
                String id = beanEle.attributeValue("id");
                String className = beanEle.attributeValue("class");
                Bean bean = new Bean();
                bean.setId(id);
                bean.setClassname(className);

                //获取当前bean下的所有属性节点property
                List<Element> proList = beanEle.elements("property");  //用一个集合存当前bean的property
                if (proList != null) {
                    for (Element proEle : proList) {                              //一个bean可以有多个property 所以也得用一个循环
                        Property prop = new Property();
                        prop.setName(proEle.attributeValue("name"));
                        prop.setValue(proEle.attributeValue("value"));
                        prop.setRef(proEle.attributeValue("ref"));
                        bean.getProperties().add(prop);  //把取出的这一条property 给当前的bean添加上
                    }
                }

                //bean的id不能重复
                if(configMap.containsKey(id)){
                    throw new RuntimeException("bean的id重复了:"+id);
                }


                //将bean封装到Map里
                configMap.put(id,bean);

            }
        }
        return configMap;



}
}
/*
  这个类主要是帮助解析Xml文件，用到了dom4j,xpath  将xml文件解析成一个哈希表，键名是beanid，值是bean类型
 */
