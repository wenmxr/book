package basis.lambda.stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author qinwen
 * @Date 2022/3/28 10:30 上午
 */
public class MockListStream {
    public static void main(String[] args) throws JsonProcessingException {

        MyList<Person> personMyList = new MyList<>();
        personMyList.add(new Person("李健", 46));
        personMyList.add(new Person("周深", 28));
        personMyList.add(new Person("张学友", 59));

        // 需求：过滤出年龄大于40的歌手的名字
        MyList<String> result = personMyList.filter(person -> person.getAge() > 40).map(Person::getName);
        prettyPrint(result.getMyList());
    }

    private static void prettyPrint(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
    }


}

@Getter
class MyList<T> {
    private List<T> myList = new ArrayList<>();

    public boolean add(T t) {
        return myList.add(t);
    }


    /**
     * 给MyList传递具体的判断规则，然后MyList把内部现有符合判断（true）的元素集合返回
     *
     * @param predicate
     * @return
     */
    public MyList<T> filter(Predicate<T> predicate) {
        MyList<T> filteredList = new MyList<>();

        for (T t : myList) {
            if (predicate.test(t)) {
                filteredList.add(t);
            }
        }

        return filteredList;
    }

    /**
     * 把MyList中的List<T>转为List<R>
     *
     * @param mapper
     * @param <R>
     * @return
     */
    public <R> MyList<R> map(Function<T, R> mapper) {
        MyList<R> mappedList = new MyList<>();

        for (T t : myList) {
            mappedList.add(mapper.apply(t));
        }

        return mappedList;
    }
}
