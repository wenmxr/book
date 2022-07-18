package improve.concurrent.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author qinwen
 * @Date 2022/6/15 10:23 上午
 */
public class LockSupportDemo {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                System.out.println("我是" + Thread.currentThread().getName() + "，我开始工作啦～");
                LockSupport.park();
                System.out.println("我是" + Thread.currentThread().getName() + "，我又活过来了～");
            });
            thread.start();
            list.add(thread);
        }

        TimeUnit.SECONDS.sleep(3);

        System.out.println("所有线程都阻塞了，3秒后恢复");

        for (Thread thread : list) {
            LockSupport.unpark(thread);
        }

        TimeUnit.SECONDS.sleep(3);

    }
}
