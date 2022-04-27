package web.listener;

/**
 * 测试
 * 观察者模式
 *
 * @Author qinwen
 * @Date 2022/4/21 3:09 下午
 */
public class ObserverTest {
    public static void main(String[] args) {
        // 被监听对象
        Thief thief = new Thief();

        // 监听器，直接new一个接口的匿名类对象
        ThiefListener thiefListener = new ThiefListener() {
            @Override
            public void shot(Event event) {
                // 这里并没有用到event 但是可以从event中取出事件源
                System.out.println("哒哒哒" + event.getThief());
            }
        };

        // 注册监听
        thief.registerListener(thiefListener);

        thief.steal();

        System.out.println(thief);

    }
}
