package ioc;

import java.lang.reflect.Method;

public class BeanUtil {

    public static Method getSetterMethod(Object obj  ,String methodName){
        Class class1 = obj.getClass();
        return getSetterMethod(class1,methodName);  //如果是对象就获得该对象的类名  再调用下面的方法↓
    }

    //参数class1是由bean的class反射出来的类  参数methodName是该bean某一个property的name
    public static Method getSetterMethod(Class class1 , String methodName){ //如果参数是类名 就获得该类的setter
        //先用property的name 得到对应的set方法名
        String temp = methodName.substring(0,1).toUpperCase();
        methodName = "set"+temp+methodName.substring(1);
        Method[] methods = class1.getMethods();
        Method method = null;
        //在这个类的所有方法中查找 如果有和上面拼接的set方法名相同的方法就把它取出来作为返回值
        for(Method m : methods){
            if(methodName.equals(m.getName())){
                method = m;
            }
        }
        if(method == null){
            throw new RuntimeException("不存在该方法："+methodName);
        }

        return method;
    }
}
//beanUtil主要是查找某一个property的set方法