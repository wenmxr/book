package basis.annotations.mytest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 自建测试框架：读取注解并执行
 *
 * @Author qinwen
 * @Date 2021/12/16 10:48 下午
 */
public class MyTestFrameWork {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        // 1、先找到测试类的字节码
        Class clz = TestDemo.class;
        // 2、反射获取测试类
        Object obj = clz.newInstance();
        // 3、获取测试类的所有公共方法
        Method[] methods = clz.getMethods();

        // 4、迭代出每一个Method对象，判断出哪些方法上使用了@MyBefore/@MyTest/@MyAfter注解
        List<Method> beforeMethods = new ArrayList();
        List<Method> testMethods = new ArrayList();
        List<Method> afterMethods = new ArrayList();

        for (Method method : methods) {
            if (method.isAnnotationPresent(MyBefore.class)) {
                // 存储使用了@MyBefore注解的方法对象
                beforeMethods.add(method);
            } else if (method.isAnnotationPresent(MyTest.class)) {
                // 存储使用了@MyTest注解的方法对象
                testMethods.add(method);
            } else if (method.isAnnotationPresent(MyAfter.class)) {
                // 存储使用了@MyAfter注解的方法对象
                afterMethods.add(method);
            }
        }

        // 5、执行方式测试
        for (Method method : testMethods) {
            // 先执行 @MyBefore的方法
            for (Method beforeMethod : beforeMethods) {
                beforeMethod.invoke(obj);
            }
            // 执行测试方法
            method.invoke(obj);
            // 最后执行 @MyAfter的方法
            for (Method afterMethod : afterMethods) {
                afterMethod.invoke(obj);
            }
        }

    }
}
