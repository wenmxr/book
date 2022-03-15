package basis.lambda.one;

/**
 * @Author qinwen
 * @Date 2022/3/14 6:20 下午
 */
public class MyThread {
    public void run() {
        System.out.println("去12306买票");
        System.out.println("坐火车");
    }
    public void start() {
        this.run();
    }
}
