package basis.lambda.two;

/**
 * @Author qinwen
 * @Date 2022/3/14 6:31 下午
 */
public class MyThread {

    private MyRunnable target;

    public MyThread(MyRunnable myRunnable) {
        this.target = myRunnable;
    }

    // MyThread自己的run()，现在基本不用了
    public void run() {
        System.out.println("去12306买了一张票");
        System.out.println("坐火车...");
    }

    /**
     * 如果
     */
    public void start() {
        if (target != null) {
            target.run();
        } else {
            this.run();
        }
    }

}
