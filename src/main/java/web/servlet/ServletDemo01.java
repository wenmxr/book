package web.servlet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;

/**
 * @Author qinwen
 * @Date 2022/4/14 4:52 下午
 */
public class ServletDemo01 {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        // 模拟容器的扫描过程，假设得到了所有的Class（包含Servlet类和普通类，普通类是用来做对照的，实际开发项目中肯定不全是Servlet）
        List<Class<?>> servletList = scanServletsInPath();
        System.out.println("在container中线程执行到main方法啦：" + Thread.currentThread().getName());

        // 从中选出Servlet：类上标注了@MyWebServletAnnotation注解 && 继承自MyHttpServlet
        for (Class<?> clz : servletList) {
            boolean hasMyWebServletAnnotation = clz.isAnnotationPresent(MyWebServletAnnotation.class);
            boolean extendsHttpServlet = MyHttpServlet.class.isAssignableFrom(clz);
            if (hasMyWebServletAnnotation && extendsHttpServlet) {
                // 确定是一个Servlet 为它创建实例并调用init
                MyHttpServlet servletObj = (MyHttpServlet) clz.newInstance();
                servletObj.init();
            }
        }
    }

    private static List<Class<?>> scanServletsInPath() {
        // 假装扫描到了Class
        return Arrays.asList(MyBookServlet.class, MyOtherClass.class);
    }
}

/**
 * 山寨的Servlet接口，你可以在JDK的javax包下找到正版的Servlet，它定义了5个生命周期方法
 * 这里我们只定义一个init方法，其他方法同理，不做演示
 */
interface MyServlet {
    void init();
}

/**
 * 模拟javax包的GenericServlet
 */
abstract class MyGenericServlet implements MyServlet {
    @Override
    public void init() {
        System.out.println("线程执行到GenericServlet.init方法啦：" + Thread.currentThread().getName());
        System.out.println("如果子类不重写，那么你就会看到GenericServlet的init方法被调用");
        System.out.println("由" + this.getClass().getName() + "调用");
    }
}


/**
 * 模拟javax包的HttpServlet
 */
abstract class MyHttpServlet extends MyGenericServlet {
}

/**
 * 很多时候我们会抽取一个BaseServlet，把公共的代码抽取一下，这里不做任何处理，就是一个空类
 */
class MyBaseServlet extends MyHttpServlet {
}

/**
 * 假设这是我们自己写的Servlet，类似于BookController
 * 使用@MyWebServletAnnotation标识这是一个Servlet，让Tomcat容器帮我们创建和管理
 */
@MyWebServletAnnotation
class MyBookServlet extends MyBaseServlet {

    @Override
    public void init() {
        System.out.println("重写了init方法" + this.getClass().getName());
    }

}

/**
 * 用来做对照，无意义
 */
class MyOtherClass {
}

/**
 * 山寨版@WebServlet
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface MyWebServletAnnotation {
    String name() default "";
}


