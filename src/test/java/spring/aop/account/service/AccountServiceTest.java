package spring.aop.account.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author qinwen
 * @Date 2022/6/9 8:03 上午
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:bean7.xml")
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;


    @Test
    public void transfer() {
        accountService.transfer("aaaa", "bbbb", 100);
    }
}