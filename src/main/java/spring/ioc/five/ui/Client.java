package spring.ioc.five.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.ioc.five.service.AccountService;

/**
 * <p>IOC中的标签 和 对象管理细节</p>
 *
 * @Author qinwen
 * @Date 2022/3/11 7:39 上午
 */
public class Client {
    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean5.xml");

        AccountService accountService = applicationContext.getBean("accountService", AccountService.class);
        AccountService accountService1 = applicationContext.getBean("accountService", AccountService.class);

        System.out.println(accountService == accountService1);

        accountService.saveAccount();

        applicationContext.close();
    }
}

