package spring.aop.cglib;

import spring.aop.proxy.IProducer;

/**
 * 一个生产者
 *
 * @Author qinwen
 * @Date 2022/6/10 10:37 上午
 */
public class Producer{

    /**
     * 销售
     */
    public void saleProduct(float money) {
        System.out.println("销售产品，拿到钱：" + money);
    }

    /**
     * 售后
     */
    public void afterProduct(float money) {
        System.out.println("提供售后服务，并拿到钱：" + money);
    }
}
