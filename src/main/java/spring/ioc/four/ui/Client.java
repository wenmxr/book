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
 * 耦合：程序间的依赖关系
 * <br/>
 * 解耦：降低程序间的依赖关系
 *
 * <ol>
 *     <li>
 *         利用反射创建对象，避免使用new关键字
 *     </li>
 *     <li>
 *         通过读取配置文件来获取要创建的对象的全限定类名
 *     </li>
 * </ol>
 *
 * <p>
 *     实际开发中应该做到编译期不依赖，运行期才依赖
 * </p>
 *
 * <p>利用IOC解耦</p>
 *
 * @Author qinwen
 * @Date 2022/3/11 7:39 上午
 */
public class Client {
    public static void main(String[] args) throws Exception {
        // AccountService accountService = new AccountServiceImpl(); // 此处有依赖关系待解决

        // 使用 ApplicationContext 接口 就是在获取spring容器 一读取完配置文件立即创建对象

        // ClassPathXmlApplicationContext 加载类路径下的配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");

        // FileSystemXmlApplicationContext 加载任意路径下的配置文件
        ApplicationContext applicationContext1 = new FileSystemXmlApplicationContext("/Users/mac/Desktop/Java_project/book/src/main/resources/bean.xml");

        // 根据bean 的 id 获取对象
        // 方式一
        AccountService accountService = (AccountService) applicationContext.getBean("accountService");
        System.out.println(accountService);

        // 方式二
        AccountDao accountDao = applicationContext.getBean("accountDao", AccountDao.class);
        System.out.println(accountDao);

        accountService.saveAccount();

        // 使用 BeanFactory 获取spring容器 什么时候使用 什么时候创建对象

        Resource resource = new ClassPathResource("bean.xml");
        BeanFactory beanFactory = new XmlBeanFactory(resource);
        AccountService accountService1 = beanFactory.getBean("accountService", AccountService.class);
        accountService1.saveAccount();

    }
}
