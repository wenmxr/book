package basis.proxy.dynamic;

import basis.proxy.sta.Calculator;
import basis.proxy.sta.CalculatorImpl;
import basis.proxy.sta.CalculatorProxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author qinwen
 * @Date 2022/3/7 7:16 上午
 */
public class Proxy01 {
    public static void main(String[] args) throws Exception {
        // 1.得到代理对象
        Class<?> proxyClass = Proxy.getProxyClass(Calculator.class.getClassLoader(), Calculator.class);
        // 2.得到唯一的又参构造方法 com.sun.proxy.$Proxy0(interface java.lang.reflect.InvocationHandler) 和反射的method有点像，可以理解为得到对应的构造器执行器
        Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
        // 3.用构造器执行器执行构造方法，得到代理对象。构造器需要 InvocationHandler 入参
        Calculator calculatorProxyImpl = (Calculator) constructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 最low的方案 反射创建一个目标对象
                CalculatorImpl calculator = new CalculatorImpl();
                // 方法前打印
                System.out.println("start " + method.getName() + "...");
                // 反射执行目标对象方法
                Object result = method.invoke(calculator, args);
                System.out.println(result);
                // 方法后打印
                System.out.println("end " + method.getName() + " ...");
                return result;
            }
        });

        calculatorProxyImpl.add(1, 2);
        calculatorProxyImpl.subtract(1, 2);
    }
}
