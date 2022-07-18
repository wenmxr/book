package spring.annotations.withoutxml.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @Author qinwen
 * @Date 2022/5/11 3:58 下午
 */
public class JdbcConfig {

    @Value("${jdbc.driver}")
    private String jdbcDriver;

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    /***
     * 用于创建一个QueryRunner对象（多例）
     * @param dataSource
     * @return
     */
    @Bean("runner")
    @Scope("prototype")
    public QueryRunner createQueryRunner(@Qualifier("ds2") DataSource dataSource) {
        return new QueryRunner(dataSource);
    }

    /**
     * 创建数据源对象
     * @return
     */
    @Bean
    public ComboPooledDataSource createPooledDataSource() {
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://rm-******.mysql.rds.aliyuncs.com:3306/dbdms?serverTimezone=GMT%2B8");
            ds.setUser("dbdms");
            ds.setPassword("******");
            return ds;
        } catch (PropertyVetoException e) {
            throw new RuntimeException();
        }
    }

    /**
     * 创建数据源对象
     * @return
     */
    @Bean(name = "ds2")
    public ComboPooledDataSource createPooledDataSource1() {
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass(jdbcDriver);
            ds.setJdbcUrl(jdbcUrl);
            ds.setUser(username);
            ds.setPassword(password);
            return ds;
        } catch (PropertyVetoException e) {
            throw new RuntimeException();
        }
    }
}
