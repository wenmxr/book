package basis.genericity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射绕过泛型检查
 *
 * @Author qinwen
 * @Date 2022/1/13 11:28 上午
 */
public class GenericClassDemo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");

        // 编译器会阻止
        // list.add(1);

        // 但泛型约束只存在于编译器，底层仍是object，所以运行期可以放入任何类型元素
        Method method = list.getClass().getDeclaredMethod("add", Object.class);
        method.invoke(list, 1);

        // 自动装箱 Integer.valueOf(1)
        Object obj = 1;

        System.out.println(obj.getClass());

        // 打印观察
        for (Object s : list) {
            System.out.println(s);
            System.out.println(s.getClass());
            // System.out.println(s instanceof String);
        }
    }
}
