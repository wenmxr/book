package design.mode.behavior.observer;

/**
 * 观察者的顶层接口
 * 这个接口相当于群（管理者）
 *
 * @Author qinwen
 * @Date 2022/4/21 5:18 下午
 */
public interface ObserverInterface<T> {
    /**
     * 注册监听者
     */
    void registerListener(T t);

    /**
     * 移除监听者
     */
    void removeListener(T t);

    /**
     * 通知监听者
     */
    void notifyListener(DataEvent event);

}
