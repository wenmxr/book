package basis.lambda.three;

/**
 * 一个需求：出行方式有很多 汽车 火车 飞机
 *
 * 策略模式
 *
 * @Author qinwen
 * @Date 2022/3/15 6:03 下午
 */
public class Demo {
    public static void main(String[] args) {
        new TravelContext(new ByAir()).travel();
        new TravelContext(new ByCar()).travel();
        new TravelContext(new ByTairn()).travel();
    }
}
