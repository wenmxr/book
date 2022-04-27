package spring.annotations.xml.ui;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.annotations.xml.service.AccountService;

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
 * <p>程序间有依赖关系</p>
 *
 * @Author qinwen
 * @Date 2022/3/11 7:39 上午
 */
public class Client {
    public static void main(String[] args) throws Exception {

        // ApplicationContext applicationContext = new ClassPathXmlApplicationCatext("bean1.xml");
        ClassPathXmlApplicationContext  applicationContext = new ClassPathXmlApplicationContext("bean1.xml");

        AccountService accountService = (AccountService) applicationContext.getBean("accountServiceImpl");
        AccountService accountService2 = (AccountService) applicationContext.getBean("accountServiceImpl");

        accountService.saveAccount();

        // System.out.println(accountService == accountService2);

        applicationContext.close();
    }
}
