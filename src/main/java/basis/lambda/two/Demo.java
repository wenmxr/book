package basis.lambda.two;

/**
 * @Author qinwen
 * @Date 2022/3/14 6:21 下午
 */
public class Demo {
    public static void main(String[] args) {
        new MyThread(new ByAir()).start();
        new MyThread(new ByTrain()).start();
    }
}
