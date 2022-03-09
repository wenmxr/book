package basis.proxy.dynamic;

import basis.proxy.sta.Calculator;
import basis.proxy.sta.CalculatorImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;

/**
 * 动态代理测试类
 *
 * @Author qinwen
 * @Date 2022/3/5 5:46 下午
 */
public class ProxyTest {
    public static void main(String[] args) throws Exception {

        /**
         * 获取Class对象的三种方法
         * 1、Class.forName(xxx)
         * 2、xxx.class
         * 3、xxx.getClass()
         */

        /**
         * Calculator接口的Class对象
         */
        Class<Calculator> calculatorClass = Calculator.class;
        // 获取构造器
        Constructor<?>[] constructors = calculatorClass.getConstructors();
        // 获取方法
        Method[] methods = calculatorClass.getMethods();
        System.out.println("————接口Class的构造器信息————");
        printClassInfo(constructors);
        System.out.println("————接口Class的方法信息————");
        printClassInfo(methods);

        /**
         * Calculator实现类的Class对象
         */
        Class<CalculatorImpl> calculatorImplClass = CalculatorImpl.class;
        // 获取构造器对象
        Constructor<?>[] calculatorImplClassConstructors = calculatorImplClass.getConstructors();
        // 获取方法
        Method[] calculatorImplClassMethods = calculatorImplClass.getMethods();
        System.out.println("————实现类Class的构造器信息————");
        printClassInfo(calculatorImplClassConstructors);
        System.out.println("————实现类Class的方法信息————");
        printClassInfo(calculatorImplClassMethods);

        /**
         * 代理Class对象
         * param1: 接口的类加载器
         * param2：代理对象和目标对象实现相同的接口
         */
        Class<?> proxyClass = Proxy.getProxyClass(Calculator.class.getClassLoader(), Calculator.class);
        // 获取构造器对象
        Constructor<?>[] proxyClassConstructors = proxyClass.getConstructors();
        // 获取方法
        Method[] proxyClassMethods = proxyClass.getMethods();
        System.out.println("————代理Class的构造器信息");
        printClassInfo(proxyClassConstructors);
        System.out.println("————代理Class的方法信息");
        printClassInfo(proxyClassMethods);

        // 得到唯一的有参构造 $Proxy(InvocationHandler h)，和反射的Method有点像，可以理解为得到对应的构造器执行器
        Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);

        Calculator calculatorProxyImpl = (Calculator) constructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return 10086;
            }
        });

        System.out.println(calculatorProxyImpl.add(1, 2));
        System.out.println(calculatorProxyImpl.subtract(1, 2));

    }

    private static void printClassInfo(Executable[] executables) {
        for (Executable executable : executables) {
            // 构造器/方法名称
            String name = executable.getName();
            StringBuilder sb = new StringBuilder(name);
            sb.append("(");
            Class<?>[] parameterTypes = executable.getParameterTypes();
            // 拼接参数
            for (Class<?> parameterType : parameterTypes) {
                sb.append(parameterType).append(",");
            }
            // 删除最后一个参数的逗号
            if (parameterTypes.length != 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(")");
            System.out.println(sb);
        }
    }

}
