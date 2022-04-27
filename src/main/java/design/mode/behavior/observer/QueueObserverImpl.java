package design.mode.behavior.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 启动一个线程 循环阻塞队列的观察者 可以实现异步解耦
 *
 * @Author qinwen
 * @Date 2022/4/22 11:31 上午
 */
public class QueueObserverImpl implements ObserverInterface<MyListener> {

    public QueueObserverImpl() {
        initObserver();
    }

    /**
     * 监听者的注册列表
     */
    private List<MyListener> listenerList = new ArrayList<>();

    /**
     * 创建一个大小为10的阻塞队列
     */
    private LinkedBlockingQueue<DataEvent> queue = new LinkedBlockingQueue<>(10);

    /**
     * 创建一个线程池
     */
    private ExecutorService executorService = new ScheduledThreadPoolExecutor(1, r -> {
        Thread t = new Thread(r);
        t.setName("thread-pool");
        t.setDaemon(false);
        return t;
    });

    @Override
    public void registerListener(MyListener myListener) {
        listenerList.add(myListener);
    }

    @Override
    public void removeListener(MyListener myListener) {
        listenerList.remove(myListener);
    }

    @Override
    public void notifyListener(DataEvent event) {
        System.out.println("向队列放入DataMsg：" + event.getMsg());
        queue.offer(event);
    }

    public void initObserver() {
        System.out.println("初始化启动一个线程");
        executorService.submit(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " 循环从阻塞队列中获取数据，take 没有数据就会阻塞");
                DataEvent event = queue.take();
                System.out.println(Thread.currentThread().getName() + " 从阻塞队列中获取数据：" + event.getMsg());
                eventNotify(event);
            }
        });
    }

    private void eventNotify(DataEvent event) {
        System.out.println(Thread.currentThread().getName() + " 循环所有监听者");
        for (MyListener myListener : listenerList) {
            myListener.onEvent(event);
        }
    }
}
