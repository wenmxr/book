package improve.concurrent.multithreading;

/**
 * @Author qinwen
 * @Date 2022/6/8 10:32 上午
 */
public class RunnableDemo01 {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 0; i < 60; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
