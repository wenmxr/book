package spring.ioc.four.service.impl;

import spring.ioc.four.dao.AccountDao;
import spring.ioc.four.dao.impl.AccountDaoImpl;
import spring.ioc.four.service.AccountService;

/**
 * @Author qinwen
 * @Date 2022/3/11 7:28 上午
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao = new AccountDaoImpl(); //此处有依赖关系待解决

    public AccountServiceImpl() {
        System.out.println("create object");
    }

    @Override
    public void saveAccount() throws Exception {
        accountDao.saveAccount();
    }
}
