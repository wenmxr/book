package basis.lambda.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author qinwen
 * @Date 2022/3/24 10:46 上午
 */
@FunctionalInterface
public interface Predicate<T> {
    /**
     * 定义一个test方法，传入任意对象，返回true or false，具体逻辑由子类实现
     * @param t
     * @return
     */
    boolean test(T t);
}

/**
 * Predicate的实现类 泛型规定只处理Person
 */
class PredicateImpl implements Predicate<Person> {

    @Override
    public boolean test(Person person) {
        return person.getAge() > 18;
    }
}

@Data
@AllArgsConstructor
class Person {
    private String name;
    private int age;
}