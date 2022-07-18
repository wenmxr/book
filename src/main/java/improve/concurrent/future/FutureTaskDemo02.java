package improve.concurrent.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * FutureTask 是如何包装 Runnable / Callable
 *
 * @Author qinwen
 * @Date 2022/6/14 5:58 下午
 */
public class FutureTaskDemo02 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // FutureTask包装Runnable（通过构造器）
        FutureTask<String> futureTask = new FutureTask<>(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "========>正在执行");
            }
        }, "runnable-result");

        // FutureTask包装Callable（通过构造器）
        FutureTask<String> futureTask1 = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName() + "========>正在执行");
                TimeUnit.SECONDS.sleep(2);
                return "callable-result";
            }
        });

        new Thread(futureTask).start();
        new Thread(futureTask1).start();

        System.out.println(futureTask.get());
        System.out.println(futureTask1.get());
    }

}
