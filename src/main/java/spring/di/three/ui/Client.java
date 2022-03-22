package spring.di.three.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.di.three.service.AccountService;

/**
 * <p>IOC中的标签 和 对象管理细节</p>
 *
 * @Author qinwen
 * @Date 2022/3/11 7:39 上午
 */
public class Client {
    public static void main(String[] args) throws Exception {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean6.xml");

        AccountService accountService = applicationContext.getBean("accountService", AccountService.class);

        accountService.saveAccount();
    }
}

