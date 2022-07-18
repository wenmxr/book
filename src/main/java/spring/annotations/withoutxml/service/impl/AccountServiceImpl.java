package spring.annotations.withoutxml.service.impl;

import org.springframework.stereotype.Service;
import spring.annotations.withoutxml.dao.IAccountDao;
import spring.annotations.withoutxml.domain.Account;
import spring.annotations.withoutxml.service.IAccountService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 账户的业务层实现类
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    @Resource
    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer acccountId) {
        accountDao.deleteAccount(acccountId);
    }
}
