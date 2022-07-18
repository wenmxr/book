package improve.concurrent.blockingqueue;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 固定容量的同步容器
 * 面试题：写一个固定容量的同步容器，拥有put和get方法，以及getCount方法，能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * @Author qinwen
 * @Date 2022/6/14 10:06 上午
 */
public class SynchronizationContainer<T> {
    private LinkedList<T> list = new LinkedList<>();

    /**
     * 固定容量 最多十个元素
     */
    private int MAX = 10;

    private int count = 0;

    public synchronized void put(T t) {

        // 为什么使用while 不用if
        // While的话，线程被唤醒后得到执行权会再次进行判断，这在高并发情景下是必须的。因为在这个线程休眠期间，极有可能条件已经不成立，此时不应该继续向下执行。如果重新读取判断条件，即可避免重复执行。
        while (list.size() == MAX) {
            try {
                System.out.println(Thread.currentThread().getName() + " wait");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        count++;

        // 通知消费者线程进行消费
        this.notifyAll();
    }

    public synchronized T get() {
        T t;
        while (list.size() == 0) {
            try {
                System.out.println(Thread.currentThread().getName() + " wait");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = list.removeFirst();
        count--;

        // 通知生产者线程进行生产
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        SynchronizationContainer<String> container = new SynchronizationContainer<>();

        // 启动消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(Thread.currentThread().getName() + " get " + container.get());
                }
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 启动生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    System.out.println(Thread.currentThread().getName() + " put " + j);
                    container.put(Thread.currentThread().getName() + " " + j);
                }
            }, "p" + i).start();

        }
    }
}
