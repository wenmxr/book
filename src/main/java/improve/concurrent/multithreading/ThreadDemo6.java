package improve.concurrent.multithreading;

import java.util.concurrent.TimeUnit;

/**
 * 子类同步方法synchronized method m可以调用父类的synchronized method m吗（super.m()）？
 *
 * 子类对象初始化前，会调用父类构造方法，在结构上相当于包裹了一个父类对象，用的都是this指针
 * @Author qinwen
 * @Date 2022/6/13 5:08 下午
 */
public class ThreadDemo6 {

    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1");
    }

    public static void main(String[] args) {
        ThreadDemo6 threadDemo6 = new SubThreadDemo6();
        threadDemo6.m1();

        new Thread(new ThreadDemo6()::m1, "t1").start();
    }
}

class SubThreadDemo6 extends ThreadDemo6 {

    @Override
    public synchronized void m1() {
        System.out.println("sub m1 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.m1();
        System.out.println("sub m1 end");
    }

}
