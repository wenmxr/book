package improve.concurrent.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @Author qinwen
 * @Date 2022/5/11 10:28 上午
 */
public class Demo01 {

    @Test
    public void test() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        nums.parallelStream().forEach(num -> System.out.println(Thread.currentThread().getName() + " >> " + num));
    }

    @Test
    public void test1() {
        Thread thread = new Thread(() -> stream1(), "thread-1");
        thread.start();

        Thread thread1 = new Thread(() -> stream2(), "thread-2");
        thread1.start();

        // Waits for this thread to die.
        // join()方法的作用： 让调用该方法的线程在执行完run()方法后，再执行join()后面的方法
        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void stream1() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        nums.parallelStream().forEach(num -> {
            System.out.println("第一次请求并发：" + Thread.currentThread().getName() + " >> " + num);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void stream2() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        nums.parallelStream().forEach(num -> {
            System.out.println("第二次请求并发：" + Thread.currentThread().getName() + " >> " + num);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
