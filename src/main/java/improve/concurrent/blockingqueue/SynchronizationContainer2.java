package improve.concurrent.blockingqueue;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 提供ReentrantLock版本，可以精准唤醒生产者线程/消费者线程
 *
 * @Author qinwen
 * @Date 2022/6/14 2:13 下午
 */
public class SynchronizationContainer2<T> {
    private LinkedList<T> list = new LinkedList<>();

    private int MAX = 10;

    private int count = 0;

    private Lock lock = new ReentrantLock();

    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t) {
        try {
            lock.lock();
            while (list.size() == MAX) {
                producer.await();
            }
            list.add(t);
            count++;
            // 通知消费者线程进行消费
            consumer.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        T t = null;

        try {
            lock.lock();
            while (list.size() == 0) {
                consumer.await();
            }
            t = list.removeFirst();
            count--;

            // 通知生产者进行生产
            producer.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        SynchronizationContainer2<String> container2 = new SynchronizationContainer2<>();
        // 启动消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                //消费线程每个线程消费5条消息
                for (int j = 0; j < 5; j++) {
                    System.out.println(container2.get());
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
                // 生产者线程每个线程生产25条消息
                for (int j = 0; j < 25; j++) {
                    container2.put(Thread.currentThread().getName() + " " + j);
                }
            }, "p" + i).start();
        }
    }
}
