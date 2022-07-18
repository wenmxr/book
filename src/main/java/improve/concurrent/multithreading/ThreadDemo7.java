package improve.concurrent.multithreading;

import java.util.concurrent.TimeUnit;

/**
 * 静态同步方法 和 非静态同步方法 互斥吗 ？
 *
 * 不是同一把锁 不互斥
 *
 * @Author qinwen
 * @Date 2022/6/13 2:45 下午
 */
public class ThreadDemo7 {
    public static void main(String[] args) {

        ThreadDemo7 threadDemo5 = new ThreadDemo7();
        new Thread(threadDemo5::m1, "t1").start();
        m2();
    }

    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1 start");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
    }

    private static synchronized void m2() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2");
    }
}
