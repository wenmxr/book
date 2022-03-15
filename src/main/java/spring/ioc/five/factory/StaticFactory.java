package spring.ioc.five.factory;

import spring.ioc.five.service.AccountService;
import spring.ioc.five.service.impl.AccountServiceImpl;

/**
 * 模拟一个静态工厂，创建一个实现类
 *
 * @Author qinwen
 * @Date 2022/3/15 3:21 下午
 */
public class StaticFactory {
    public static AccountService createAccountService() {
        return new AccountServiceImpl();
    }
}
