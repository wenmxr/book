package spring.annotations.xml.dao.impl;

import org.springframework.stereotype.Repository;
import spring.annotations.xml.dao.AccountDao;

/**
 * @Author qinwen
 * @Date 2022/3/11 7:30 上午
 */
@Repository
public class AccountDaoImpl2 implements AccountDao {
    @Override
    public void saveAccount() {
        System.out.println("保存账户");
    }
}
