package design.mode.behavior.observer;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;

/**
 * @Author qinwen
 * @Date 2022/4/21 6:38 下午
 */
public class ObserverDemo {
    public static void main(String[] args) {
        // 管理者 群
        ObserverInterface<MyListener> loopObserver = new LoopObserverImpl();

        // 观察者 成员
        MyListener scoreListener = new MyListener() {
            @Override
            public void onEvent(DataEvent event) {
                if (event instanceof ScoreDataEvent) {
                    System.out.println("积分处理：" + event.getMsg());
                    // 积分逻辑
                    ((ScoreDataEvent) event).setScore(1);
                    System.out.println("积分：" + ((ScoreDataEvent) event).getScore());
                }
            }
        };

        // 观察者 成员
        MyListener smsListener = new MyListener() {
            @Override
            public void onEvent(DataEvent event) {
                if (event instanceof SmsDataEvent) {
                    System.out.println("短信处理：" + event.getMsg());
                    // 短信逻辑
                    ((SmsDataEvent) event).setPhoneNumber("10086");
                    System.out.println("给" + ((SmsDataEvent) event).getPhoneNumber() + "发短信");
                }
            }
        };
        //
        // // 订阅 加群
        // loopObserver.registerListener(scoreListener);
        // loopObserver.registerListener(smsListener);
        //
        // // 事件 积分群消息
        // DataEvent scoreDataEvent = new ScoreDataEvent();
        // scoreDataEvent.setMsg("循环同步观察者");
        //
        // // 事件 短信群消息
        // DataEvent smsDataEvent = new SmsDataEvent();
        // smsDataEvent.setMsg("开始了");
        //
        // // 发布 发送积分消息
        // loopObserver.notifyListener(scoreDataEvent);
        // // 发布 发送短信消息
        // loopObserver.notifyListener(smsDataEvent);



        // ==========================================


        ObserverInterface<MyListener> queueObserver = new QueueObserverImpl();

        queueObserver.registerListener(scoreListener);
        queueObserver.registerListener(smsListener);

        DataEvent scoreDataEvent1 = new ScoreDataEvent();
        scoreDataEvent1.setMsg("队列异步观察者");

        DataEvent smsDataEvent1 = new SmsDataEvent();
        smsDataEvent1.setMsg("队列异步观察者");

        queueObserver.notifyListener(scoreDataEvent1);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        queueObserver.notifyListener(smsDataEvent1);
    }
}
