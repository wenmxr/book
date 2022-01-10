package basis.reflect.myjpa;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

/**
 * @Author qinwen
 * @Date 2021/12/23 4:59 下午
 */
public class BaseDao<T> {
    private static BasicDataSource dataSource;

    // 静态代码块 设置连接数据库参数

    static {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://rm-2ze611a1zde9eq4f7.mysql.rds.aliyuncs.com:3306/dbdms?serverTimezone=GMT%2B8");
        dataSource.setUsername("dbdms");
        dataSource.setPassword("7Zffm1alajxUN3yr");
    }

    /**
     * 得到jdbcTemplate
     */
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    /**
     * DAO操作的对象
     */
    private Class<T> beanClass;

    /**
     * 构造器
     * 初始化时完成对实际类型参数的获取
     * 例如：BaseDao<User>，那么baseClass就是user.class
     */
    public BaseDao() {
        beanClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void add(T bean) {
        // 得到User对象的所有字段
        Field[] fields = bean.getClass().getDeclaredFields();

        // 拼接sql语句，表名直接用POJO的类名，所以创建表时，请注意写成User，而不是t_user
        StringBuilder sql = new StringBuilder("insert into ").append(beanClass.getSimpleName()).append(" values(");
        for (int i = 0; i < fields.length; i++) {
            sql.append("?");
            if (i < fields.length -1) {
                 sql.append(",");
            }
        }
        sql.append(")");

        // 获取bean字段的值（要插入的记录）
        ArrayList<Object> paramList = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object o = field.get(bean);
                paramList.add(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        Object[] params = paramList.toArray();
        int num = jdbcTemplate.update(sql.toString(), params);
        System.out.println(num);
    }
}
