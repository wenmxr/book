package web.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 责任链实现类
 * 计数器方式
 *
 * @Author qinwen
 * @Date 2022/3/8 3:37 下午
 */
public class MyFilterChain2 implements MyFilterChain {

    private final List<MyFilter> filterList = new ArrayList<>();

    /**
     * 类成员变量 可能会出现线程安全问题
     */
    private int index = 0;

    private boolean target = true;

    @Override
    public void appendFilter(MyFilter myFilter) {
        filterList.add(myFilter);
    }

    public MyFilter getFilter() {
        return filterList.get(index);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response) {
        if (index < filterList.size()) {
            MyFilter filter = getFilter();
            index++;
            filter.doFilter(request, response, this);
        }
        if (index == filterList.size() && target) {
            System.out.println("执行目标方法");
            target = false;
        }
    }
}
