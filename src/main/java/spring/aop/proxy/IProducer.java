package spring.aop.proxy;

/**
 * 对生产厂家要求的接口
 *
 * @Author qinwen
 * @Date 2022/6/10 10:46 上午
 */
public interface IProducer {
    /**
     * 销售
     * @param money
     */
    void saleProduct(float money);

    /**
     * 售后
     * @param money
     */
    void afterProduct(float money);
}
