package basis.lambda.three;

/**
 * 具体策略类 具体的策略实现
 *
 * @Author qinwen
 * @Date 2022/3/15 6:09 下午
 */
public class ByTairn implements TravelStrategy {
    @Override
    public void travel() {
        System.out.println("火车");
    }
}
