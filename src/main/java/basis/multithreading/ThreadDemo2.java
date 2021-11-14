package basis.multithreading;

/**
 * 多线程创建的方式二：实现Runnable接口，实现run()方法
 *
 * @Author qinwen
 * @Date 2021/11/14 1:46 下午
 */
public class ThreadDemo2 implements Runnable{
    public static void main(String[] args) {
        System.out.println("Thread-main is running");
        // ThreadDemo2实现Runnable接口，实现run()方法
        ThreadDemo2 threadDemo2 = new ThreadDemo2();
        // 调用Thread的构造方法，传入threadDemo2对象，创建线程对象
        Thread t1 = new Thread(threadDemo2);
        // 开启线程：threadDemo2 得到 cpu的执行权 执行run方法
        t1.start();
    }
    public void run() {
        System.out.println("Thread-0 is running");
    }
}
