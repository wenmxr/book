package basis.lock;

import lombok.SneakyThrows;
import org.openjdk.jol.info.ClassLayout;

/**
 * @Author qinwen
 * @Date 2021/12/1 9:49 下午
 */
public class Test {
    public static void main(String[] args) {

        // 无锁
        // noLock();

        /**
         * 偏向锁：仅有一个线程进入临界区
         * 假如，我家很穷，家里只有一个碗，当我一个人在家时，没人会和我抢碗，这时即为偏向锁状态
         */
        // biasedLock();

        /**
         * 轻量级锁：多个线程交替进入临界区
         * 假如，我家很穷，家里只有一个碗，当我和女朋友都在家，她还不饿，那我就先吃，我吃完了，等她饿了，再用我的碗去吃饭，这时即为轻量级锁状态
         */
        // lightWeight();

        /**
         * 重量级锁：多个线程同时进入临界区
         * 假如，我家很穷，家里只有一个碗，当我和女朋友都在家，我俩都很饿，这时就会去争抢这唯一的碗吃饭，这时即为重量级锁状态
         */
        heavyWeight();
    }

    /**
     * 重量级锁：多个线程同时进入临界区
     */
    private static void heavyWeight() {
        Object o = new Object();
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(() -> print(o));
            t.start();
        }
    }

    public static void print(Object o) {
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    /**
     * 轻量级锁：多个线程交替进入临界区
     */
    @SneakyThrows
    private static void lightWeight() {
        Thread.sleep(5000);
        Object o = new Object();
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        Thread t = new Thread(() -> print(o));
        t.start();
    }


    /**
     * 偏向锁：仅有一个线程进入临界区
     */
    @SneakyThrows
    private static void biasedLock() {
        // jvm默认延时4s自动开启偏向锁 等待jvm开启偏向锁
        Thread.sleep(5000);
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    /**
     * 无锁
     */
    private static void noLock() {
        Object object = new Object();
        System.out.println("hash: " + object.hashCode());
        System.out.println("hash: " + Integer.toBinaryString(object.hashCode()));

        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }

}
