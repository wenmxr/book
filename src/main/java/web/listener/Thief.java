package web.listener;

/**
 * 被监听的对象
 *
 * @Author qinwen
 * @Date 2022/4/21 3:07 下午
 */
public class Thief {

    ThiefListener thiefListener;

    /**
     * 注册监听器方法
     *
     * @param thiefListener
     */
    public void registerListener(ThiefListener thiefListener) {
        this.thiefListener = thiefListener;
    }

    public void steal() {
        // "偷之前 告诉警察"
        if (thiefListener != null) {
            Event event = new Event(this);
            // "开枪啊"
            thiefListener.shot(event);
        }
        System.out.println("开始偷东西～");
    }
}
