package improve.concurrent.multithreading;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 线程执行的方法
 *
 * @Author qinwen
 * @Date 2022/6/14 3:59 下午
 */
public class ThreadDemo0 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 方式一：重写Thread#run()
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "========>正在执行");
            }
        };
        thread.start();

        // 方式二：构造方法传入Runnable实例
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "========>正在执行");
        }).start();

        // 方式三：线程池 + Callable/Runnable 这里以Callable为例
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> submit = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "========>正在执行");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "success";
        });

        String result = submit.get();
        System.out.println("result========>" + result);
        // 关闭线程池
        executorService.shutdown();
    }
}
