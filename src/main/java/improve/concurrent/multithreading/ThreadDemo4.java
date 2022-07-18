package improve.concurrent.multithreading;

/**
 * 同步与非同步方法是否可以同时调用？
 *
 * 不共用同一把锁，可以同时调用
 *
 * @Author qinwen
 * @Date 2021/11/26 1:59 上午
 */
public class ThreadDemo4 {

    public static void main(String[] args) {
        ThreadDemo4 t = new ThreadDemo4();

        new Thread(t::m1, "t1").start();
        new Thread(t::m2, "t2").start();
    }

    /**
     * 锁对象为this
     */
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1 start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end");
    }

    public void m2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2 start");
    }
}
