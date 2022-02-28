package basis.proxy.sta;

/**
 * @Author qinwen
 * @Date 2022/2/28 8:21 上午
 */
public class App {
    public static void main(String[] args) {

        // 静态代理
        Calculator calculator = new CalculatorProxy(new CalculatorImpl());
        System.out.println(calculator.add(1, 2));
        System.out.println(calculator.subtract(2, 1));
    }
}
