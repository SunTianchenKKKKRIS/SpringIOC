这个demo是模拟Spring的IOC，也就是注入依赖的原理，通过做这个笔记来加深理解
代码参考至https://blog.csdn.net/JobsandCzj/article/details/70176627?ops_request_misc=%7B%22request%5Fid%22%3A%22158298421619726867834544%22%2C%22scm%22%3A%2220140713.130056874..%22%7D&request_id=158298421619726867834544&biz_id=0&utm_source=distribute.pc_search_result.none-task


Demo的基本思路是：
                   1.利用dom4j和Xpath来扫描XML文件，并将其中的Bean解析为一个Hashmap<BeanID,Bean>
                   2.初始化一个IOC容器  Hashmap<BeanID,Object> 其中对象是第三步反射出来的对象
                   3.(1)通过Bean的Class属性找到实体类，再利用反射实例化一个对象
                     (2.1)如果属性是value(在实际对象中是String)  就根据Property的id字符串拼接出该属性的setter 并注入
                     (2.2)如果属性是ref(在实际对象中是引用类型)  就在ioc容器中取出上面已注入的对象 再根据Property的id字符串拼接出该属性的setter 并注入
                     //这与value和引用的注入顺序无关  引用和被引用谁在前面都可以