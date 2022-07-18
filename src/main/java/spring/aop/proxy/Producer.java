package spring.aop.proxy;

import basis.enumeration.MemberDemo;

/**
 * 一个生产者
 *
 * @Author qinwen
 * @Date 2022/6/10 10:37 上午
 */
public class Producer implements IProducer{

    /**
     * 销售
     */
    @Override
    public void saleProduct(float money) {
        System.out.println("销售产品，拿到钱：" + money);
    }

    /**
     * 售后
     */
    @Override
    public void afterProduct(float money) {
        System.out.println("提供售后服务，并拿到钱：" + money);
    }
}
