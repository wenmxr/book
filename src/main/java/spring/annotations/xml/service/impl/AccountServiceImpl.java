package spring.annotations.xml.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import spring.annotations.xml.dao.AccountDao;
import spring.annotations.xml.service.AccountService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * 账户的业务层实现类
 *
 *  曾经的XML配置
 *  <bean id="accountService" class="spring.example.xml.service.impl.AccountServiceImpl" scope="" init-method="" destroy-method="">
 *      <property name="account" value="" | ref="">
 *      </property>
 *  </bean>
 *
 *  用于创建对象 <bean></bean>
 *      Component：
 *          作用：相当于把当前类存入spring容器中
 *          属性：value 用于指定bean的id 默认为当前类名首字母改小写
 *      Controller、Service、Repository 三层注解 等同于 Component
 *
 *  用于注入数据 <property></property>
 *      Autowired:
 *          spring注解
 *          作用：自动按照类型注入，只要容器中有唯一个bean对象类型与要注入的变量类型匹配，就可以注入成功
 *              如果相同类型的bean对象有多个，再按照变量名称比较，相同则注入
 *      Qualifier:
 *          spring注解
 *          在按照类型注入的基础上，再按照名称注入，value属性用于指定 bean的id 配合Autowired使用
 *      Resource:
 *          java自带注解
 *          作用：直接按照bean的id注入，找不到对应的id再找类型是否唯一，可以单独使用
 *          属性：
 *              name: 可以指定id
 *      以上注解只能注入bean类型的数据，不可注入 基本类型和String，集合只能通过xml
 *      Value:
 *          用于注入基本类型和String
 *          属性：
 *              value：用于指定数据的值， 可以使用spring的EL表达式 ${表达式}
 *
 *  用于改变作用范围 <scope></scope>
 *      Scope：
 *          作用：用于指定bean的作用范围
 *          属性：
 *              value：指定范围的取值 常用取值：singleton prototype
 *
 *  和生命周期相关 <init-method></init-method> <destroy-method></destroy-method>
 *      PreDestroy:
 *          作用：指定销毁方法   单例对象跟随容器一起销毁  多例对象会被GC回收
 *      PostConstruct
 *          作用：指定初始化方法
 *
 *
 * @Author qinwen
 * @Date 2022/3/11 7:28 上午
 */
@Component
@Scope("prototype")
public class AccountServiceImpl implements AccountService {

    @Resource(name = "accountDaoImpl")
    private AccountDao accountDao;

    @Override
    public void saveAccount() throws Exception {

        // ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean1.xml");
        // AccountDao accountDao = applicationContext.getBean("accountDaoImpl", AccountDaoImpl.class);

        System.out.println("对象创建了");
        accountDao.saveAccount();
    }

    @PreDestroy
    public void destroy() {
        System.out.println("对象销毁了");
    }

    @PostConstruct
    public void init() {
        System.out.println("对象生成了");
    }
}
