package improve.concurrent.multithreading;

/**
 * 多线程创建的方式一：继承Thread类，重写run()方法
 *
 * @Author qinwen
 * @Date 2021/11/14 1:29 下午
 */
public class ThreadDemo1 extends Thread {

    public static void main(String[] args) {
        System.out.println("Thread-main is running");
        // ThreadDemo1 继承了 Thread类 并重写了 run()
        ThreadDemo1 threadDemo1 = new ThreadDemo1();
        // 开启线程 threadDemo1线程 会得到cpu的执行权 执行run() 中的代码
        threadDemo1.start();
    }

    @Override
    public void run() {
        System.out.println("Thread-0 is running");
    }
}
