package spring.ioc.three.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <p>
 * 一个创建Bean对象的工厂
 * </p>
 * <ul>
 *     <Li>
 *         Bean: 在计算机英语中，有可重用组件的意义
 *     </Li>
 *     <li>
 *         JavaBean: 用Java语言编写的Bean
 *     </li>
 * </ul>
 *
 * <p>当前环境下，就是我们要创建的service、dao对象</p>
 *
 * <ol>
 *     <li>
 *         需要一个配置文件来配置我们需要的service、dao对象
 *         <br/>
 *         配置的内容：唯一标识=全限定类名（key=value）
 *     </li>
 *     <li>
 *         通过配置文件中的配置内容（全限定类名）反射创建对象
 *         <br/>
 *         配置文件可以是xml or properties
 *     </li>
 * </ol>
 *
 * @Author qinwen
 * @Date 2022/3/11 8:27 上午
 */
public class BeanFactory {

    /**
     * 定义一个Properties对象
     */
    private static final Properties PROPERTIES;

    private static Map<String, Object> beans;

    // 使用静态代码块为Properties对象赋值
    static {
        InputStream is = null;
        try {
            // 实例化对象
            PROPERTIES = new Properties();
            // 获取properties文件的流对象
            is = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            PROPERTIES.load(is);

            // 实例化容器
            beans = new HashMap<>();
            // 获取配置文件中所有的key
            Enumeration<Object> keys = PROPERTIES.keys();
            // 遍历枚举
            while (keys.hasMoreElements()) {
                // 取出每一个key
                String beanName = keys.nextElement().toString();
                // 根据key获取value
                String beanPath = PROPERTIES.getProperty(beanName);
                // 反射创建对象
                Object bean = Class.forName(beanPath).newInstance();
                // 将每一个key value 存入容器
                beans.put(beanName, bean);
            }

        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化properties失败");
        } finally {
            try {
               if (is != null) {
                   is.close();
               }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据bean名称获取bean对象
     *
     * @param beanName 名称
     * @return 对象
    public static Object getBean(String beanName) {
        Object obj = null;
        try {
            String beanPath = PROPERTIES.getProperty(beanName);
            // System.out.println("beanPath=" + beanPath);
            // 每次都会调用默认构造函数 形成多例对象
            obj = Class.forName(beanPath).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    */

    /**
     * 根据bean名称获取bean对象
     *
     * @param beanName 名称
     * @return 对象
     */
    public static Object getBean(String beanName) {
        return beans.get(beanName);
    }
}
