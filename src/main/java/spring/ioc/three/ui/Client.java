package spring.ioc.three.ui;

import spring.ioc.three.factory.BeanFactory;
import spring.ioc.three.service.AccountService;

/**
 * 耦合：程序间的依赖关系
 * <br/>
 * 解耦：降低程序间的依赖关系
 *
 * <ol>
 *     <li>
 *         利用反射创建对象，避免使用new关键字
 *     </li>
 *     <li>
 *         通过读取配置文件来获取要创建的对象的全限定类名
 *     </li>
 * </ol>
 *
 * <p>
 *     实际开发中应该做到编译期不依赖，运行期才依赖
 * </p>
 *
 * <p>工厂模式解耦 第二版</p>
 *
 * @Author qinwen
 * @Date 2022/3/12 9:39 上午
 */
public class Client {
    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 5; i++) {
            AccountService accountService = (AccountService) BeanFactory.getBean("accountService");
            System.out.println(accountService);
            accountService.saveAccount();
        }
    }
}
