package spring.ioc.five.factory;

import spring.ioc.five.service.AccountService;
import spring.ioc.five.service.impl.AccountServiceImpl;

/**
 * 模拟一个实例工厂 创建业务层实现类
 * 此工厂创建对象，必须先有工厂实例对象，再调用方法
 *
 *
 * @Author qinwen
 * @Date 2022/3/15 3:43 下午
 */
public class InstanceFactory {
    public AccountService createAccountService() {
        return new AccountServiceImpl();
    }
}
