package design.mode.behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建管理者的实现类
 * 相当于具体的群 钉钉群、微信群
 *
 * @Author qinwen
 * @Date 2022/4/21 6:09 下午
 */
public class LoopObserverImpl implements ObserverInterface<MyListener>{

    /**
     * 监听者的注册列表
     */
    private final List<MyListener> listenerList = new ArrayList<>();

    @Override
    public void registerListener(MyListener myListener) {
        listenerList.add(myListener);
    }

    @Override
    public void removeListener(MyListener myListener) {
        listenerList.remove(myListener);
    }

    @Override
    public void notifyListener(DataEvent event) {
        for (MyListener listener : listenerList) {
            listener.onEvent(event);
        }
    }
}
