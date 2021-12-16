package art.of.concurrent.programming.chapter01;

/**
 * 线程上下文
 *
 * @Author qinwen
 * @Date 2021/12/15 7:56 上午
 */
public class ThreadContext {
    
    private static int cnt;

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                cnt += i;
            }
        }, "t1").start();
        for (int i = 0; i < 10000; i++) {

        }
    }
}
