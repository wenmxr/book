package design.mode.behavior.observer;

/**
 * 定义抽象的监听者接口
 * 相当于群成员（监听者）
 *
 * @Author qinwen
 * @Date 2022/4/21 5:27 下午
 */
public interface MyListener {
    void onEvent(DataEvent event);
}
