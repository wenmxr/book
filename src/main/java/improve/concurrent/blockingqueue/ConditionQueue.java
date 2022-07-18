package improve.concurrent.blockingqueue;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞唤醒 ReentrantLock的condition版本
 *
 * @Author qinwen
 * @Date 2022/7/15 3:25 下午
 */
public class ConditionQueue<T> {

    /**
     * 容器
     */
    private final LinkedList<T> queue = new LinkedList<>();

    /**
     * 显示锁，相对于synchronized锁为隐示锁
     */
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition producerCondition = lock.newCondition();
    private final Condition consumerCondition = lock.newCondition();

    public void put(T resource) {
        lock.lock();
        try {
            while (queue.size() >= 1) {
                // 队列满了，不能再塞东西了，轮询等待消费者取出数据
                System.out.println("生产者：队列已满，无法插入");
                // 生产者阻塞
                producerCondition.await();
            }

            System.out.println("生产者插入资源" + resource + "！！！");
            queue.addFirst(resource);
            // 生产完毕，唤醒消费者
            consumerCondition.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void take() {
        lock.lock();
        try {
            while (queue.size() <= 0) {
                // 队列空了，不能再消费，轮询等待生产者插入数据
                System.out.println("消费者：队列空了，无法取出");
                // 消费者阻塞
                consumerCondition.await();
            }

            System.out.println("消费者：取出消息");
            queue.removeFirst();
            // 消费完毕，唤醒生产者
            producerCondition.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
