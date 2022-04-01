package basis.lambda.stream;

/**
 * @Author qinwen
 * @Date 2022/3/28 11:07 上午
 */
public interface Function<E, R> {
    /**
     * 定义一个apply() 方法 接受一个E 返回一个R 也就是把E映射成R
     */
    R apply(E e);
}

class FunctionImpl implements Function<Person, Integer> {

    @Override
    public Integer apply(Person person) {
        return person.getAge();
    }
}