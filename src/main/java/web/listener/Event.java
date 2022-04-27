package web.listener;

/**
 * 事件
 * 就是包装后的被监听对象
 *
 * @Author qinwen
 * @Date 2022/4/21 3:08 下午
 */
public class Event {
    private Thief thief;

    public Event(Thief thief) {
        this.thief = thief;
    }

    public Thief getThief() {
        return thief;
    }

    public void setThief(Thief thief) {
        this.thief = thief;
    }
}
