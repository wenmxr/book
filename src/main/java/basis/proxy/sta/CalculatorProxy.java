package basis.proxy.sta;

/**
 * @Author qinwen
 * @Date 2022/2/28 8:16 上午
 */
public class CalculatorProxy implements Calculator{

    private Calculator calculator;

    public CalculatorProxy(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public int add(int a, int b) {
        System.out.println("start add ...");
        int result = calculator.add(a, b);
        System.out.println("end add ...");
        return result;
    }

    @Override
    public int subtract(int a, int b) {
        System.out.println("start subtract ...");
        int result = calculator.subtract(a, b);
        System.out.println("end subtract ...");
        return result;
    }
}
