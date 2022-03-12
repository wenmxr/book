package spring.two.service.impl;

import spring.two.dao.AccountDao;
import spring.two.factory.BeanFactory;
import spring.two.service.AccountService;

/**
 * @Author qinwen
 * @Date 2022/3/11 7:28 上午
 */
public class AccountServiceImpl implements AccountService {

    // private AccountDao accountDao = new AccountDaoImpl(); //此处有依赖关系待解决
    private AccountDao accountDao = (AccountDao) BeanFactory.getBean("accountDao");

    @Override
    public void saveAccount() throws Exception {
        // Class<?> aClass = Class.forName("spring.two.dao.impl.AccountDaoImpl");
        // accountDao = (AccountDao) aClass.newInstance();
        accountDao.saveAccount();
    }
}
