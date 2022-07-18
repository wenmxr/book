package improve.concurrent.multithreading;

/**
 * Thread(Runnable 对象){重写run}
 * <p>
 * 这个题目的关键在于run()方法的覆盖。上面代码，虽然传入了Runnable实例，但是Thread类原来的run()已经被重写了。
 * 原本Thread的run：target.run()，会调用Runnable对象。
 * 但是重写run后，就没有target.run()，而是简单的打印语句。所以即使传入Runnable对象，也不会调用它里面的run()。
 * <p>
 * 不管有几种实现多线程的方式，Thread类永远是入口，Thread的run()永远是开头。代码能否被执行到，全看run()。
 * 你要么写在Thread的run()中，要么让Thread的run()去调到你写的代码(targer.run())。
 *
 * @Author qinwen
 * @Date 2021/11/15 12:06 上午
 */
public class ThreadDemo3 {
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable's run method is running");
            }
        }) {
            @Override
            public void run() {
                System.out.println("Thread's run method is running");
            }

        }.start();
    }
}
