package spring.aop.account.service;

import spring.aop.account.domain.Account;

import java.util.List;

/**
 * 账户的业务层接口
 */
public interface AccountService {

    /**
     * 查询所有
     * @return
     */
    List<Account> findAllAccount();

    /**
     * 查询一个
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 保存
     * @param account
     */
    void saveAccount(Account account);

    /**
     * 更新
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除
     * @param acccountId
     */
    void deleteAccount(Integer acccountId);

    /**
     * 转账
     *
     * @param sourceName 转出账户
     * @param targetName 转入账户
     * @param money
     */
    void transfer(String sourceName, String targetName, float money);
}
