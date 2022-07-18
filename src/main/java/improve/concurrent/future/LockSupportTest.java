package improve.concurrent.future;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport park/unpark 与 wait/notify 的区别
 * 1、wait和notify都是Object中的方法，在调用这两个方法之前必须先获得锁对象，但是park不需要获取某个对象的锁就可以锁住线程
 * 2、notify只能随机唤醒一个线程，无法唤醒指定线程，unpark可以唤醒指定线程
 *
 * @Author qinwen
 * @Date 2022/6/15 10:38 上午
 */
public class LockSupportTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "开始运行");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "运行结束");
        }, "t1");
        thread.start();

        System.out.println("t1线程已启动，但是在内部进行了park");
        TimeUnit.SECONDS.sleep(2);
        LockSupport.unpark(thread);
        System.out.println("LockSupport进行了unpark");
    }
}
