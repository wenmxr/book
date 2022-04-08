package basis.lambda;

/**
 * 函数式接口
 *
 * @Author qinwen
 * @Date 2022/4/8 3:09 下午
 */
public class Example {
    public static void main(String[] args) {
        System.out.println(concat("a", "b"));
        System.out.println(concat("a", "b", (s1, s2) -> s1 + "_" + s2));
    }


    /**
     * 字符串拼接 写死
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String concat(String s1, String s2) {
        return s1 + "-" + s2;
    }

    /**
     * 字符串拼接 通用
     *
     * @param s1
     * @param s2
     * @param concator
     * @return
     */
    public static String concat(String s1, String s2, Concator concator) {
        return concator.concat(s1,s2);
    }

}

@FunctionalInterface
interface Concator {
    String concat(String s1, String s2);
}


