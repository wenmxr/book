package basis.proxy.sta;

/**
 * 计算器
 *
 * @Author qinwen
 * @Date 2022/2/27 9:47 上午
 */
public class CalculatorImpl implements Calculator{
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) {
        return a - b;
    }
}
