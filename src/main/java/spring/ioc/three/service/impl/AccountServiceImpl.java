package spring.ioc.three.service.impl;

import spring.ioc.three.factory.BeanFactory;
import spring.ioc.three.dao.AccountDao;
import spring.ioc.three.service.AccountService;

/**
 * @Author qinwen
 * @Date 2022/3/11 7:28 上午
 */
public class AccountServiceImpl implements AccountService {

    // 避免定义类成员变量 可能会造成线程安全问题
    // private int i = 1;

    private AccountDao accountDao = (AccountDao) BeanFactory.getBean("accountDao");

    @Override
    public void saveAccount() throws Exception {
        int i = 1;
        accountDao.saveAccount();
        System.out.println(i);
    }
}
