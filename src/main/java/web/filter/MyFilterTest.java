package web.filter;

/**
 * 山寨过滤器实现
 *
 * @Author qinwen
 * @Date 2022/3/7 11:09 上午
 */
public class MyFilterTest {
    public static void main(String[] args) {
        chain(new MyFilterChain1());

        new Thread(new Runnable() {
            @Override
            public void run() {
                MyFilterChain2 myFilterChain2 = new MyFilterChain2();
                myFilterChain2.appendFilter(new MyFilterImpl1());
                myFilterChain2.appendFilter(new MyFilterImpl2());
                myFilterChain2.appendFilter(new MyFilterImpl3());
                // 这里实际会传入rquest和response对象。现在只能不传，但是不影响过滤器的是实现。
                myFilterChain2.doFilter(null, null);
            }
        }).start();

        new Thread(() -> {
            MyFilterChain2 myFilterChain2 = new MyFilterChain2();
            myFilterChain2.appendFilter(new MyFilterImpl1());
            myFilterChain2.appendFilter(new MyFilterImpl2());
            myFilterChain2.appendFilter(new MyFilterImpl3());
            // 这里实际会传入rquest和response对象。现在只能不传，但是不影响过滤器的是实现。
            myFilterChain2.doFilter(null, null);
        }).start();

    }

    private static void chain(MyFilterChain filterChain) {
        filterChain.appendFilter(new MyFilterImpl1());
        filterChain.appendFilter(new MyFilterImpl2());
        filterChain.appendFilter(new MyFilterImpl3());
        // 这里实际会传入rquest和response对象。现在只能不传，但是不影响过滤器的是实现。
        filterChain.doFilter(null, null);
    }
}
