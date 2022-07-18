package spring.aop.proxy;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

/**
 * 模拟一个消费者
 *
 * @Author qinwen
 * @Date 2022/6/10 10:47 上午
 */
public class Client {
    public static void main(String[] args) {
        final Producer producer = new Producer();
        /**
         * 动态代理
         *  特点：字节码随用随创建，随用随加载
         *  作用：在不修改源码的基础上，对方法增强
         *  分类：
         *      基于接口的动态代理
         *      基于子类的动态代理
         *  基于接口的动态代理
         *      涉及的类：Proxy
         *      提供者：JDK官方
         *  如何创建代理对象
         *      使用Proxy类中的newProxyInstance()方法
         *  创建代理对象的要求
         *      被代理类至少实现一个接口，如果没有则不能使用
         *  newProxyInstance()参数
         *      ClassLoader：类加载器
         *          它是用于加载代理对象字节码的。和被代理对象使用的是相同加载器。（固定写法）
         *      Class[]：字节码数组
         *          它是用于让代理对象和被代理对象有相同的方法（固定写法）
         *      InvocationHandler：用于提供增强代码
         *          它是让我们写如何代理。我们一般是写一些接口的实现类，通常是匿名内部类
         *          实现类谁用谁写
         */

        IProducer proxyProducer = (IProducer) Proxy.newProxyInstance(producer.getClass().getClassLoader(),
                producer.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 作用：被代理对象的任何接口方法 执行的时候都会经过该方法
                     *
                     * @param proxy  代理对象的引用
                     * @param method 当前执行的方法
                     * @param args   当前执行方法所需的参数
                     * @return       和被代理对象的方法有相同的返回值
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 提供增强的代码
                        Object returnValue = null;

                        // 1、获取方法执行的参数
                        Float money = (Float) args[0];
                        // 2、判断当前方法是不是销售
                        if ("saleProduct".equals(method.getName())) {
                            returnValue = method.invoke(producer, money * 0.8f);
                        } else  {
                            returnValue = method.invoke(producer, money);
                        }

                        return returnValue;
                    }
                });

        proxyProducer.saleProduct(10000f);
        proxyProducer.afterProduct(10000f);
    }
}
