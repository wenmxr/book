package web.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Author qinwen
 * @Date 2022/3/8 11:04 上午
 */
public interface MyFilter {
    void doFilter(ServletRequest request, ServletResponse response, MyFilterChain chain);
}
