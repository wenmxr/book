package improve.concurrent.multithreading;

import java.util.concurrent.TimeUnit;

/**
 * 同一个类中的synchronized method m1 可以调用 synchronized method m2 吗 ？
 * <p>
 * 一个同步方法可以调用另一个同步方法，一个线程已经拥有了某个对象的锁，再次申请的时候仍然会得到该对象的锁，也就是说synchronized获得的锁是可重入的
 *
 * 普通方法的synchronized的锁 就是this对象
 *
 * @Author qinwen
 * @Date 2022/6/13 2:45 下午
 */
public class ThreadDemo5 {
    public static void main(String[] args) {

        ThreadDemo5 threadDemo5 = new ThreadDemo5();
        new Thread(threadDemo5::m1, "t1").start();
        new Thread(threadDemo5::m3, "t2").start();
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

    private synchronized void m2() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2");
        m3();
    }

    private synchronized void m3() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m3");
    }
}
