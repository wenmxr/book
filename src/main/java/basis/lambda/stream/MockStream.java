package basis.lambda.stream;

/**
 * @Author qinwen
 * @Date 2022/3/24 11:06 上午
 */
public class MockStream {
    public static void main(String[] args) {
        Person person = new Person("qw", 18);

        // 1、具体实现类 调用test方法
        Predicate<Person> personPredicate = new PredicateImpl();
        myPrint(person, personPredicate);

        Function<Person, Integer> integerFunction = new FunctionImpl();
        myPrint(person, integerFunction);

        // 2、匿名内部类 调用test方法
        Predicate<Person> personPredicate1 = new Predicate<Person>() {
            @Override
            public boolean test(Person person) {
                return person.getAge() < 18;
            }
        };
        myPrint(person, personPredicate1);

        Function<Person, Integer> integerFunction1 = new Function<Person, Integer>() {
            @Override
            public Integer apply(Person person) {
                return person.getAge();
            }
        };

        myPrint(person, integerFunction1);


        // 3、Lambda 表达式 调用test方法
        Predicate<Person> personPredicate2 = person1 -> person1.getAge() == 18;
        myPrint(person, personPredicate2);

        Function<Person, Integer> integerFunction2 = Person::getAge;
        myPrint(person, integerFunction2);

    }

    private static void myPrint(Person person, Predicate<Person> filter) {
        if (filter.test(person)) {
            System.out.println("ture");
        } else {
            System.out.println("false");
        }
    }

    private static void myPrint(Person person, Function<Person, Integer> function) {
        System.out.println(function.apply(person));
    }
}
