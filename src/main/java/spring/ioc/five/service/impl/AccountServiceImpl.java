package spring.ioc.five.service.impl;

import spring.ioc.five.service.AccountService;

/**
 * @Author qinwen
 * @Date 2022/3/11 7:28 上午
 */
public class AccountServiceImpl implements AccountService {


    public AccountServiceImpl() {
        System.out.println("create object");
    }

    @Override
    public void saveAccount() throws Exception {
        System.out.println("run save account...");
    }


    private void init() {
        System.out.println("init object...");
    }

    private void destroy() {
        System.out.println("destroy object...");
    }
}
