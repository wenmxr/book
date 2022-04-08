package basis.lambda.stream.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author qinwen
 * @Date 2022/4/1 3:57 下午
 */
public class StreamTest {
    public static void main(String[] args) {


        // test1();
        // testPeek();
    }

    public static void testPeek() {
        Stream.of(1, 2, 3, 4)
                .peek(i -> System.out.println(i + ", "))
                .filter(i -> i >= 2)
                .peek(i -> System.out.println(i + ", "))
                .filter(i -> i >= 3)
                .forEach(System.out::println);
    }

    public static void test1() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3).filter(i -> i > 1);

        // Optional<Integer> any = integerStream.findAny();
        // Optional<Integer> first = integerStream.findFirst(); // stream has already been operated upon or closed  流已经被操作或关闭

        List<Integer> collect = Stream.of(1, 2, 3).filter(i -> i > 1).collect(Collectors.toList());
        Optional<Integer> any = collect.stream().findAny();
        Optional<Integer> first = collect.stream().findFirst();
    }

    public static void testCollect() {
        // Function.identity() == v -> v
        Map<String, Person> collect = list.stream().collect(Collectors.toMap(Person::getName, Function.identity()));
    }


    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9));
        list.add(new Person("am", 19, "温州", 777.7));
        list.add(new Person("iron", 21, "杭州", 888.8));
        list.add(new Person("iron", 17, "宁波", 888.8));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    static class Person {
        private String name;
        private Integer age;
        private String address;
        private Double salary;


        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", address='" + address + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Person2 {
        private String name;
        private Integer age;
        private String address;
        private Double salary;
        // 个人标签
        private List<String> tags;
    }

    private static List<Person2> list2;

    static {
        list2 = new ArrayList<>();
        list2.add(new Person2("i", 18, "杭州", 999.9, new ArrayList<>(Arrays.asList("成年人", "学生", "男性"))));
        list2.add(new Person2("am", 19, "温州", 777.7, new ArrayList<>(Arrays.asList("成年人", "打工人", "宇宙最帅"))));
        list2.add(new Person2("iron", 21, "杭州", 888.8, new ArrayList<>(Arrays.asList("喜欢打篮球", "学生"))));
        list2.add(new Person2("man", 17, "宁波", 888.8, new ArrayList<>(Arrays.asList("未成年人", "家里有矿"))));
    }

    public static void flatMapTest() {

        // flatMap就是把多个流合并成一个流 总之，当你遇到List中还有List，然后你又想把第二层的List都拎出来集中处理时，就可以考虑用flatMap()，先把层级打平，再统一处理。
        Set<String> collect = list2.stream().flatMap(p -> p.getTags().stream()).collect(Collectors.toSet());
    }
}
