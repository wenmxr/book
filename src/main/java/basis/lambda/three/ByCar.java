package basis.lambda.three;

/**
 * 具体策略类 具体的策略实现
 *
 * @Author qinwen
 * @Date 2022/3/15 6:08 下午
 */
public class ByCar implements TravelStrategy {
    @Override
    public void travel() {
        System.out.println("汽车");
    }
}
