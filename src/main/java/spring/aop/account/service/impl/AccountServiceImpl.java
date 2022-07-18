package spring.aop.account.service.impl;

import org.springframework.stereotype.Service;
import spring.aop.account.dao.AccountDao;
import spring.aop.account.domain.Account;
import spring.aop.account.service.AccountService;
import spring.aop.account.util.TransactionManager;

import java.util.List;

/**
 * 账户的业务层实现类
 * @author mac
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;
    private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAllAccount() {
        try {
            // 1、开启事务
            txManager.beginTransaction();
            // 2、执行操作
            List<Account> accounts = accountDao.findAllAccount();
            // 3、提交事务
            txManager.commitTransaction();
            // 4、返回结果
            return accounts;
        } catch (Exception e) {
            // 5、回滚事务
            txManager.rollbackTransaction();
            throw new RuntimeException(e);
        } finally {
            // 6、释放资源
            txManager.releaseTransaction();
        }
    }

    @Override
    public Account findAccountById(Integer accountId) {
        try {
            // 1、开启事务
            txManager.beginTransaction();
            // 2、执行操作
            Account account = accountDao.findAccountById(accountId);
            // 3、提交事务
            txManager.commitTransaction();
            // 4、返回结果
            return account;
        } catch (Exception e) {
            // 5、回滚事务
            txManager.rollbackTransaction();
            throw new RuntimeException(e);
        } finally {
            // 6、释放资源
            txManager.releaseTransaction();
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            // 1、开启事务
            txManager.beginTransaction();
            // 2、执行操作
            accountDao.saveAccount(account);
            // 3、提交事务
            txManager.commitTransaction();
        } catch (Exception e) {
            // 4、回滚事务
            txManager.rollbackTransaction();
            throw new RuntimeException(e);
        } finally {
            // 5、释放资源
            txManager.releaseTransaction();
        }

    }

    @Override
    public void updateAccount(Account account) {
        try {
            // 1、开启事务
            txManager.beginTransaction();
            // 2、执行操作
            accountDao.updateAccount(account);
            // 3、提交事务
            txManager.commitTransaction();
        } catch (Exception e) {
            // 4、回滚事务
            txManager.rollbackTransaction();
            throw new RuntimeException(e);
        } finally {
            // 5、释放资源
            txManager.releaseTransaction();
        }
    }

    @Override
    public void deleteAccount(Integer accountId) {
        try {
            // 1、开启事务
            txManager.beginTransaction();
            // 2、执行操作
            accountDao.deleteAccount(accountId);
            // 3、提交事务
            txManager.commitTransaction();
        } catch (Exception e) {
            // 4、回滚事务
            txManager.rollbackTransaction();
            throw new RuntimeException(e);
        } finally {
            // 5、释放资源
            txManager.releaseTransaction();
        }

    }

    @Override
    public void transfer(String sourceName, String targetName, float money) {
        try {
            // 1、开启事务
            txManager.beginTransaction();
            // 2、执行操作

            // 2.1、查询转出账户
            Account source = accountDao.findAccountsByName(sourceName);
            // 2.2、查询转入账户
            Account target = accountDao.findAccountsByName(targetName);
            // 2.3、转出账户减钱
            source.setMoney(source.getMoney() - money);
            // 2.4、转入账户加钱
            target.setMoney(target.getMoney() + money);
            // 2.5、更新转出账户
            accountDao.updateAccount(source);

            int i = 1 / 0;

            // 2.6、更行转入账户
            accountDao.updateAccount(target);
            // 3、提交事务
            txManager.commitTransaction();
        } catch (Exception e) {
            // 4、回滚事务
            txManager.rollbackTransaction();
            throw new RuntimeException(e);
        } finally {
            // 5、释放资源
            txManager.releaseTransaction();
        }

    }
}
