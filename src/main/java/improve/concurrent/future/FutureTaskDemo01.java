package improve.concurrent.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 假设一个场景，要求设计一个方法，专门用来执行耗时操作。但不同点在于：
 *      调用时必须能够立即返回result
 *      拿到result后，我可以在随后任意时刻通过result.get()获取耗时任务的最终结果（异步结果）
 *
 * 初识FutureTask
 *  就像一个魔术盒 一开始是空的 你走几步 再打开 就出现了一个苹果
 *
 * @Author qinwen
 * @Date 2022/6/14 5:10 下午
 */
public class FutureTaskDemo01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 将Callable 塞进了 FutureTask
        FutureTask<String> futureTask =  new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName() + "========>正在执行");
                TimeUnit.SECONDS.sleep(3);
                return "success";
            }
        });

        // 将FutureTask 塞进了 Thread
        new Thread(futureTask).start();
        System.out.println(Thread.currentThread().getName() + "========>任务启动");

        // 获取异步结果
        String result = futureTask.get();
        System.out.println("任务执行结束，result====>" + result);
    }
}
