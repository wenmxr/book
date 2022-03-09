package web.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Author qinwen
 * @Date 2022/3/8 11:10 上午
 */
public class MyFilterImpl1 implements MyFilter{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, MyFilterChain chain) {
        System.out.println("过滤器 "+ this.getClass().getSimpleName() +" 执行前");
        chain.doFilter(request, response);
        System.out.println("过滤器 "+ this.getClass().getSimpleName() +" 执行后");
    }
}
