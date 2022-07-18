package spring.aop.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.cglib.proxy.Proxy;
import spring.aop.proxy.IProducer;

import java.lang.reflect.Method;

/**
 * 模拟一个消费者
 *
 * @Author qinwen
 * @Date 2022/6/10 10:47 上午
 */
public class Client {
    public static void main(String[] args) {
        Producer producer = new Producer();
        /**
         * 动态代理
         *  特点：字节码随用随创建，随用随加载
         *  作用：在不修改源码的基础上，对方法增强
         *  分类：
         *      基于接口的动态代理
         *      基于子类的动态代理
         *  基于子类的动态代理
         *      涉及的类：Enhancer
         *      提供者：第三方cglib库
         *  如何创建代理对象
         *      使用Enhancer类中的create方法
         *  创建代理对象的要求
         *      被代理类不能是最终类
         *  create()参数
         *      Class：字节码
         *          它是用于指定被代理对象的字节码
         *
         *      Callback：用于提供增强代码
         *          它是让我们写如何代理。我们一般是写一些接口的实现类，通常是匿名内部类
         *          实现类谁用谁写
         *          我们一般写的都是该接口的子接口实现类：MethodInterceptor
         */

        Producer cglibProducer = (Producer) Enhancer.create(producer.getClass(), new MethodInterceptor() {
            /**
             * 执行被代理对象的任何方法都会经历该方法
             *
             * @param proxy  代理对象的引用
             * @param method 当前执行的方法
             * @param args   当前执行方法所需参数
             * @param methodProxy 当前执行方法的代理对象
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                // 提供增强的代码
                Object returnValue = null;

                // 1、获取方法执行的参数
                Float money = (Float) args[0];
                // 2、判断当前方法是不是销售
                if ("saleProduct".equals(method.getName())) {
                    returnValue = method.invoke(producer, money * 0.8f);
                } else {
                    returnValue = method.invoke(producer, money);
                }

                return returnValue;
            }
        });


        cglibProducer.saleProduct(12000f);
        cglibProducer.afterProduct(10000f);
    }
}
