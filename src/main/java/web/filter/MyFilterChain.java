package web.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Author qinwen
 * @Date 2022/3/8 11:08 上午
 */
public interface MyFilterChain {
    void doFilter(ServletRequest request, ServletResponse response);

    void appendFilter(MyFilter myFilter);
}
