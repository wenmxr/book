package spring.annotations.anno.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.annotations.anno.domain.Account;

import java.util.List;

/**
 * @Author qinwen
 * @Date 2022/5/5 8:07 上午
 */
public class IAccountServiceTest {
    @Test
    public void findAllAccount() {
        // 1、获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean1.xml");
        // 2、创建Service对象
        IAccountService accountService = (IAccountService) ac.getBean("accountService");
        // 3、验证方法
        List<Account> allAccount = accountService.findAllAccount();
        for (Account account : allAccount) {
            System.out.println(account);
        }
    }

    @Test
    public void findAccountById() {
        // 1、获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean1.xml");
        // 2、创建Service对象
        IAccountService accountService = (IAccountService) ac.getBean("accountService");
        // 3、验证方法
        Account account = accountService.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void saveAccount() {

        Account account = new Account();
        account.setName("test");
        account.setMoney(12345f);

        // 1、获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean1.xml");
        // 2、创建Service对象
        IAccountService accountService = (IAccountService) ac.getBean("accountService");
        // 3、验证方法
        accountService.saveAccount(account);
    }

    @Test
    public void updateAccount() {
        // 1、获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean1.xml");
        // 2、创建Service对象
        IAccountService accountService = (IAccountService) ac.getBean("accountService");
        // 3、验证方法
        Account account = accountService.findAccountById(4);
        account.setMoney(23456f);
        accountService.updateAccount(account);
    }

    @Test
    public void deleteAccount() {
        // 1、获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean1.xml");
        // 2、创建Service对象
        IAccountService accountService = (IAccountService) ac.getBean("accountService");
        // 3、验证方法
        accountService.deleteAccount(2);
    }
}