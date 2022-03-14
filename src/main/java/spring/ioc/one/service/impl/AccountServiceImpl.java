package spring.ioc.one.service.impl;

import spring.ioc.one.dao.AccountDao;
import spring.ioc.one.service.AccountService;

/**
 * @Author qinwen
 * @Date 2022/3/11 7:28 上午
 */
public class AccountServiceImpl implements AccountService {

    // private AccountDao accountDao = new AccountDaoImpl(); //此处有依赖关系待解决
    private AccountDao accountDao;

    @Override
    public void saveAccount() throws Exception {
        Class<?> aClass = Class.forName("spring.ioc.one.dao.impl.AccountDaoImpl");
        accountDao = (AccountDao) aClass.newInstance();
        accountDao.saveAccount();
    }
}
