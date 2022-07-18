package spring.aop.account.util;

import java.sql.SQLException;

/**
 * 和事务相关的工具类 它包含了开启事务 执行事务 回滚事务 释放连接
 *
 *
 * @Author qinwen
 * @Date 2022/6/10 7:39 上午
 */
public class TransactionManager {
    private ConnectionUtil connectionUtil;

    public void setConnectionUtil(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    /**
     * 开始事务
     */
    public void beginTransaction() {
        try {
            connectionUtil.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行事务
     */
    public void commitTransaction() {
        try {
            connectionUtil.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 回滚事务
     */
    public void rollbackTransaction() {
        try {
            connectionUtil.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 释放连接
     */
    public void releaseTransaction() {
        try {
            // 将连接还回连接池中
            connectionUtil.getThreadConnection().close();
            // 与线程解绑
            connectionUtil.removeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
