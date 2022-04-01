package basis.lambda.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @Author qinwen
 * @Date 2022/3/29 3:24 下午
 */
public class FunctionalInterfaceTest {
    /**
     * 给函数式接口赋值的格式：
     * 函数式接口 = 入参 -> 出参/制造出参的语句
     *
     * @param args
     */
    public static void main(String[] args) {
        FunctionalInterface1 functionalInterface1 = (str, w) -> System.out.println(str);
        FunctionalInterface2 functionalInterface2 = () -> "abc";
        FunctionalInterface3 functionalInterface3 = str -> Integer.parseInt(str);
        FunctionalInterface4 functionalInterface4 = str -> Integer.parseInt(str) > 3;

        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        list.stream().filter(i -> i > 1).map(i -> i).collect(Collectors.toList());

    }

    /**
     * 消费型 有入参 无返回值
     * (T t) -> {}
     */
    interface FunctionalInterface1 {
        void method(String str, String s);
    }

    /**
     * 供给型 无入参 有返回值
     * () -> T t
     */
    interface FunctionalInterface2 {
        String method();
    }

    /**
     * 映射型 把T转换成R返回
     * T t -> R r
     */
    interface FunctionalInterface3 {
        Integer method(String str);
    }

    /**
     * 判定型 把T转化成boolean
     * T t -> boolean
     */
    interface FunctionalInterface4 {
        boolean method(String str);
    }
}
