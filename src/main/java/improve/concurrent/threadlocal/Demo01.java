package improve.concurrent.threadlocal;

/**
 * @Author qinwen
 * @Date 2022/5/13 8:07 上午
 */
public class Demo01 {
    public static void main(String[] args) {

        ThreadLocal tl = new ThreadLocal();

        Thread t1 = new Thread(() -> {
            tl.set("泪水");

            System.out.println("wait");

            System.out.println(tl.get());


        });
        t1.start();

        // tl.set("main放入的");

        Thread t2 = new Thread(() -> tl.set("紫青宝剑"));
        t2.start();

        System.out.println(tl.get());

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finished");
    }
}
