package ioc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ClassPathXmlApplicationContext implements BeanFactory{
    private Map<String,Object> ioc;  //模拟ioc容器

    private Map<String,Bean> config;

    /*构造函数
    *1. 初始化ioc容器
    *2. 加载配置文件，生成bean对象放入ioc容器
     */


    public ClassPathXmlApplicationContext(String path) {
        ioc = new HashMap<String , Object>();  //初始化ioc容器  ioc也是一个哈希表 键是beanid值是对象

        config = XmlConfig.getConfig(path);  // 读取配置文件  这里config接的是解析类return的哈希表

        if(config!=null){
            for(Entry<String,Bean> entry : config.entrySet()){
                String beanId = entry.getKey();
                Bean bean = entry.getValue();

                Object object = createBean(bean);  //利用取出来的bean生成相应对象
                ioc.put(beanId,object);  //将对应的beanid和对应的对象放入ioc容器里
            }
        }
    }

    public Object createBean(Bean bean){                                        //根据bean生成对象实例
        String beanId = bean.getId();
        String className = bean.getClassname();  //获取bean的id和class

        Class c = null;
        Object object = null;
        try {
            c = Class.forName(className);  //将声明的class和bean的class名对应上  jvm查找和"classname"这个类名相同的类
        }catch (ClassNotFoundException e ){
            throw new RuntimeException("配置的class属性不合法："+className);
        }

        try{
            object = c.newInstance();  // ioc容器中放入的对象是由反射出来的类实例化的对象（需要无参构造方法，与spring原理相同）
        }catch (Exception e){
            throw new RuntimeException("该类缺少一个无参构造方法："+className);
        }
        //将bean的属性封装到对象中
        if(bean.getProperties()!=null){
            for(Property p : bean.getProperties()){
                //1.xml中该bean的属性使用的是value
                if(p.getValue()!=null){
                    //获取属性对应的setter
                    Method setMethod = BeanUtil.getSetterMethod(c,p.getName());
                    System.out.println("参数1是"+object.toString()+"      参数2是"+p.getValue()+"      方法名是+"+setMethod.getName());
                    try {
                        //反射调用刚才获得的方法
                        setMethod.invoke(object,p.getValue());
                        System.out.println("注入后为"+object.toString());
                    }catch (Exception e){
                        throw new RuntimeException("属性名称不合法或者没有相应setter:"+p.getName());
                    }
                }

                if (p.getRef()!=null){
                    //获取属性setter
                    Method setMethod = BeanUtil.getSetterMethod(object,p.getName());
                    //从容器中找到依赖对象
                    Object obj = ioc.get(p.getRef());
                    if (obj == null){
                        throw new RuntimeException("没有找到依赖对象:"+p.getRef());
                    }
                    else {
                        //找到依赖对象 调用set方法注入
                        try {
                            setMethod.invoke(object,obj);
                        }catch (Exception e){
                            throw new RuntimeException("属性名称不合法或者没有相应setter:"+p.getName());
                        }
                    }
                }
            }
        }
        return object;
    }

    @Override
    public Object getBean(String beanName) {

        return ioc.get(beanName);

    }
}
