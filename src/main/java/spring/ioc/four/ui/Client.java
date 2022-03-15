package spring.ioc.four.ui;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import spring.ioc.four.dao.AccountDao;
import spring.ioc.four.service.AccountService;

/**
 *
 * <p>spring工厂的类结构</p>
 *
 * <p>BeanFactory vs ApplicationContext</p>
 *
 * <ul>
 *     <li>BeanFactory 与 ApplicationContext 是父子接口的关系</li>
 *     <li>创建对象的时间不同：使用 ApplicationContext 接口 获取spring容器 一读取完配置文件就会立即创建对象； 使用 BeanFactory 接口 获取sporing容器 什么时候用什么时候创建</li>
 *     <li>默认都是单例，可以通过scope标签改变</li>
 * </ul>
 *
 * <p>ClassPathXmlApplicationContext vs FileSystemXmlApplicationContext vs AnnotationConfigApplicationContext</p>
 *
 * <ul>
 *     <li>ClassPathXmlApplicationContext：加载类路径下的配置文件，要求配置文件必须在类路径下</li>
 *     <li>FileSystemXmlApplicationContext：加载任意路径下的配置文件</li>
 *     <li>AnnotationConfigApplicationContext：基于注解配置，读取注解创建容器</li>
 * </ul>
 * @Author qinwen
 * @Date 2022/3/11 7:39 上午
 */
public class Client {
    public static void main(String[] args) throws Exception {
        // AccountService accountService = new AccountServiceImpl(); // 此处有依赖关系待解决

        // 使用 ApplicationContext 接口 就是在获取spring容器 一读取完配置文件立即创建对象

        // ClassPathXmlApplicationContext 加载类路径下的配置文件
        // ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean4.xml");
        //
        // // FileSystemXmlApplicationContext 加载任意路径下的配置文件
        // // ApplicationContext applicationContext1 = new FileSystemXmlApplicationContext("/Users/mac/Desktop/Java_project/book/src/main/resources/bean4.xml");
        //
        // // 根据bean 的 id 获取对象
        // // 方式一
        // AccountService accountService = (AccountService) applicationContext.getBean("accountService");
        // System.out.println(accountService);
        //
        // // 方式二
        // AccountDao accountDao = applicationContext.getBean("accountDao", AccountDao.class);
        // System.out.println(accountDao);
        //
        // accountService.saveAccount();

        // 使用 BeanFactory 获取spring容器 什么时候使用 什么时候创建对象

        Resource resource = new ClassPathResource("bean4.xml");
        BeanFactory beanFactory = new XmlBeanFactory(resource);
        AccountService accountService1 = beanFactory.getBean("accountService", AccountService.class);
        AccountService accountService2 = beanFactory.getBean("accountService", AccountService.class);
        accountService1.saveAccount();
        accountService2.saveAccount();
        System.out.println(accountService1 == accountService2);
        System.out.println(accountService1);
        System.out.println(accountService2);
    }
}
