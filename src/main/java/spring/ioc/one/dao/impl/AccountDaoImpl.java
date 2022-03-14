package spring.ioc.one.dao.impl;

import spring.ioc.one.dao.AccountDao;

/**
 * @Author qinwen
 * @Date 2022/3/11 7:30 上午
 */
public class AccountDaoImpl implements AccountDao {
    @Override
    public void saveAccount() {
        System.out.println("保存账户");
    }
}
