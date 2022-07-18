package spring.aop.account.util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 连接的工具类 它用于从数据源中获取一个连接 并与当前线程绑定
 *
 * @Author qinwen
 * @Date 2022/6/9 8:34 上午
 */
public class ConnectionUtil {

    private final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程上的连接
     *
     * @return
     */
    public Connection getThreadConnection() {
        try {

            // 1、先用Thread勾出Thread.threadLocals 获取当前线程上的连接
            Connection connection = threadLocal.get();
            // 2、判断当前线程上是否有连接
            if (null == connection) {
                // 3、从数据源获取一个连接 并存入到 线程中
                connection = dataSource.getConnection();
                threadLocal.set(connection);
            }
            // 4、返回线程上的连接
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 把 Connection 与 Thread 解绑
     */
    public void removeConnection() {
        threadLocal.remove();
    }



}
