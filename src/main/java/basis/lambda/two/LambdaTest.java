package basis.lambda.two;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Author qinwen
 * @Date 2022/3/16 4:46 下午
 */
public class LambdaTest {

    /**
     * hello
     * basis.lambda.two.LambdaTest$1@ee7d9f1
     * 打印的this  指的是匿名内部类对象
     *
     * @throws InterruptedException
     */
    @Test
    public void testClosure() throws InterruptedException {
        // 在匿名内部类的外面定义一个String变量
        final String str = "hello";
        // 构造一个匿名内部类对象
        MyRunnable r = new MyRunnable() {
            @Override
            public void run() {
                System.out.println(str);
                System.out.println(this);
            }
        };

        new MyThread(r).start();

        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * hello
     * basis.lambda.two.LambdaTest@2e5c649
     *
     * 打印的this 是指向方法的调用者，隐式调用
     *
     * @throws InterruptedException
     */
    @Test
    public void testClosure2() throws InterruptedException {
        final String str = "hello";
        // 构造一个匿名内部类对象
        MyRunnable r = () -> {
            System.out.println(str);
            System.out.println(this);
        };

        new MyThread(r).start();

        TimeUnit.SECONDS.sleep(1);
    }
}
