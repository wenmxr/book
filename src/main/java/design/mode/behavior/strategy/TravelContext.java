package design.mode.behavior.strategy;

/**
 * 环境类：用来操作策略的上下文
 *
 * @Author qinwen
 * @Date 2022/3/15 6:11 下午
 */
public class TravelContext {
    private TravelStrategy strategy;

    public TravelContext(TravelStrategy mode) {
        this.strategy = mode;
    }

    public void travel() {
        strategy.travel();
    }
}
