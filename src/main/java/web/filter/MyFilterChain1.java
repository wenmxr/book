package web.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.LinkedList;
import java.util.Objects;

/**
 * 责任链实现类
 * 方式一：移除方式
 *
 * @Author qinwen
 * @Date 2022/3/8 11:14 上午
 */
public class MyFilterChain1 implements MyFilterChain {

    private final LinkedList<MyFilter> filterChainList = new LinkedList<>();

    @Override
    public void appendFilter(MyFilter filter) {
        Objects.requireNonNull(filter);
        filterChainList.add(filter);
    }

    public boolean hasNextFilter() {
        return filterChainList.size() > 0;
    }

    public MyFilter getNextFilter() {
        return filterChainList.pop();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response) {
        if (hasNextFilter()) {
            getNextFilter().doFilter(request, response, this);
        } else {
            System.out.println("执行目标方法");
        }
    }
}
