package improve.concurrent.multithreading;

import lombok.SneakyThrows;

/**
 * @Author qinwen
 * @Date 2022/7/13 5:08 下午
 */
public class NewThreadTypeTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " 主线程开始");
        Worker worker = new Worker();
        worker.begin();
        System.out.println(Thread.currentThread().getName() + " 主线程结束");
        Thread.sleep(4000);
    }
}

class Worker implements Runnable {

    @SneakyThrows
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 执行Worker#run()开始");
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() + " 执行Worker#run()结束");

    }

    public void begin() {
        new Thread(this).start();
    }
}
