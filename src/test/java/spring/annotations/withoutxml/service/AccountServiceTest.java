package spring.annotations.withoutxml.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.annotations.withoutxml.config.SpringConfiguration;
import spring.annotations.withoutxml.domain.Account;

import java.util.List;

/**
 * Spring 整合 Junit
 *
 * 依赖spring框架 提供了一个运行器，可以读取配置文件或注解，创建容器，我们只需要告诉它配置文件或注解在哪就行
 * 1、导aop包
 * 2、使用@RunWith替换原有运行器
 * 3、使用@ContextConfiguration指定spring配置文件的位置
 *      locations 配置文件路径
 *      classes 注解类位置
 * 4、使用Autowired 给测试类变量注入数据
 *
 *
 * @Author qinwen
 * @Date 2022/5/18 8:50 上午
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})
public class AccountServiceTest {


    @Autowired
    private IAccountService accountService;

    @Test
    public void findAllAccount() {
        // 1、获取容器
        // ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 2、创建Service对象
        // AccountService accountService = (AccountService) ac.getBean("accountService");
        // 3、验证方法
        List<Account> allAccount = accountService.findAllAccount();
        for (Account account : allAccount) {
            System.out.println(account);
        }
    }

    @Test
    public void findAccountById() {
        // // 1、获取容器
        // ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // // 2、创建Service对象
        // AccountService accountService = (AccountService) ac.getBean("accountService");
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
        // ApplicationContext ac = new ClassPathXmlApplicationContext("bean3.xml");
        // // 2、创建Service对象
        // AccountService accountService = (AccountService) ac.getBean("accountService");
        // 3、验证方法
        accountService.saveAccount(account);
    }

    @Test
    public void updateAccount() {
        // 1、获取容器
        // ApplicationContext ac = new ClassPathXmlApplicationContext("bean3.xml");
        // // 2、创建Service对象
        // AccountService accountService = (AccountService) ac.getBean("accountService");
        // 3、验证方法
        Account account = accountService.findAccountById(4);
        account.setMoney(23456f);
        accountService.updateAccount(account);
    }

    @Test
    public void deleteAccount() {
        // 1、获取容器
        // ApplicationContext ac = new ClassPathXmlApplicationContext("bean3.xml");
        // // 2、创建Service对象
        // AccountService accountService = (AccountService) ac.getBean("accountService");
        // 3、验证方法
        accountService.deleteAccount(2);
    }
}
