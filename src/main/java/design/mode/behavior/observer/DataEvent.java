package design.mode.behavior.observer;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 定义抽象的事件接口
 * 这个接口相当于群里的通知
 *
 * @Author qinwen
 * @Date 2022/4/21 5:25 下午
 */
@Data
public abstract class DataEvent {
    private String msg;
}

/**
 * 积分事件类
 */
@Data
class ScoreDataEvent extends DataEvent {
    private Integer score;
}

/**
 * 短信事件类
 */
@Data
class SmsDataEvent extends DataEvent {
    private String phoneNumber;
}