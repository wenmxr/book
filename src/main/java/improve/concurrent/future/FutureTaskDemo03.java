package improve.concurrent.future;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Author qinwen
 * @Date 2022/6/15 11:07 上午
 */
public class FutureTaskDemo03 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 单线程的线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            System.out.println(Thread.currentThread().getName() + "=========>正在执行");
            TimeUnit.SECONDS.sleep(3);
            return "success";
        };

        // 开启异步线程
        Future<String> submit = executorService.submit(callable);

        // 主线程继续执行
        System.out.println(Thread.currentThread().getName() + "任务1");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName() + "任务2");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName() + "任务3");
        TimeUnit.SECONDS.sleep(1);

        System.out.println(submit.get());

        executorService.shutdown();
    }
}
