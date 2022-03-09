package basis.proxy.dynamic;

import basis.proxy.sta.Calculator;
import basis.proxy.sta.CalculatorImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author qinwen
 * @Date 2022/3/7 7:41 上午
 */
public class Proxy02 {
    public static void main(String[] args) throws Exception {
        CalculatorImpl target = new CalculatorImpl();
        Calculator calculatorProxy = (Calculator) getProxy(target);
        calculatorProxy.add(1, 2);
    }

    private static Object getProxy(Object target) throws Exception {
        // param1 任意的类加载器
        // param2 需要代理的接口
        Class<?> proxyClass = Proxy.getProxyClass(target.getClass().getClassLoader(), target.getClass().getInterfaces());
        Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
        return constructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("start " + method.getName() + " ...");
                Object result = method.invoke(target, args);
                System.out.println(result);
                System.out.println("end " + method.getName() + " ...");
                return result;
            }
        });
    }

}
