package basis.lambda.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Author qinwen
 * @Date 2022/3/29 4:15 下午
 */
public class ConvertUtil {
    /**
     * 将List 转 Map
     *
     * @param list
     * @param keyFunction
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> listToMap(List<V> list, Function<V, K> keyFunction) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<K, V> map = new HashMap<>(list.size());
        for (V v : list) {
            K k = keyFunction.apply(v);
            if (k == null) {
                continue;
            }
            map.put(k, v);
        }
        return map;
    }

    /**
     * 将List 转 Map
     * 可指定过滤规则
     *
     * @param list
     * @param keyFunction
     * @param predicate
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> listToMap(List<V> list, Function<V, K> keyFunction, Predicate<V> predicate) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<K, V> map = new HashMap<>(list.size());
        for (V v : list) {
            K k = keyFunction.apply(v);
            if (k == null || !predicate.test(v)) {
                continue;
            }
            map.put(k, v);
        }
        return map;
    }

    /**
     * List 映射成 List
     *
     * @param list
     * @param mapper
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<R> resultToList(List<T> list, Function<T, R> mapper) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        List<R> newList = new ArrayList<>();
        for (T t : list) {
            R r = mapper.apply(t);
            if (r == null) {
                continue;
            }
            newList.add(r);
        }
        return newList;
    }

    /**
     * List 映射成 List
     * 可指定过滤规则
     *
     * @param list
     * @param mapper
     * @param filter
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<R> resultToList(List<T> list, Function<T, R> mapper, Predicate<T> filter) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        List<R> newList = new ArrayList<>();
        for (T t : list) {
            R r = mapper.apply(t);
            if (r == null || !filter.test(t)) {
                continue;
            }
            newList.add(r);
        }
        return newList;
    }

    /**
     * foreach 内部判空
     *
     * @param originList
     * @param processor
     * @param <T>
     */
    public static <T> void foreachIfNonNull(List<T> originList, Consumer<T> processor) {
        if (originList == null || originList.isEmpty()) {
            return;
        }
        for (T t : originList) {
            if (t != null) {
                processor.accept(t);
            }
        }
    }

    public static <K, V> Map<K, List<V>> groupBy1(List<V> list, Function<V, K> keyExtractor) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<K, List<V>> map = new HashMap<>();
        for (V v : list) {
            K k = keyExtractor.apply(v);
            if (k == null) {
                continue;
            }
            List<V> vs = map.get(k);
            if (vs == null || vs.isEmpty()) {
                vs = new ArrayList<>();
                vs.add(v);
                map.put(k, vs);
            } else {
                vs.add(v);
            }
        }
        return map;
    }

    public static <K, V> Map<K, List<V>> groupBy2(List<V> list, Function<V, K> keyExtractor) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<K, List<V>> map = new HashMap<>();
        for (V v : list) {
            K k = keyExtractor.apply(v);
            if (k == null) {
                continue;
            }
            List<V> vs = map.getOrDefault(k, new ArrayList<>());
            vs.add(v);
            map.put(k, vs);
        }
        return map;
    }

    public static <K, V, R> Map<K, List<R>> groupBy(List<V> list, Function<V, K> keyExtractor, Function<V, R> valueExtractor) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<K, List<R>> map = new HashMap<>(list.size());
        for (V v : list) {
            K k = keyExtractor.apply(v);
            if (k == null) {
                continue;
            }
            R r = valueExtractor.apply(v);

            List<R> rs = map.get(k);
            if (rs == null || rs.isEmpty()) {
                rs = new ArrayList<>();
                map.put(k, rs);
            }
            rs.add(r);
        }
        return map;
    }

    public static <K, V, R> Map<K, List<R>> groupBy2(List<V> list, Function<V, K> keyExtractor, Function<V, R> valueExtractor) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<K, List<R>> map = new HashMap<>(list.size());
        for (V v : list) {
            K k = keyExtractor.apply(v);
            if (k == null) {
                continue;
            }
            /**
             * 如果有key 则get(key) 否则 put(key, v)
             */
            List<R> rs = map.computeIfAbsent(k, key -> new ArrayList<>());
            rs.add(valueExtractor.apply(v));
        }
        return map;
    }

    public static <T, K> List<T> distinct(List<T> list, Function<T, K> keyExtractor) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        LinkedHashMap<K, T> resultMap = new LinkedHashMap<>();
        for (T t : list) {
            K k = keyExtractor.apply(t);
            if (resultMap.containsKey(k)) {
                continue;
            }
            resultMap.put(k, t);
        }
        return new ArrayList<>(resultMap.values());
    }

    public static void main(String[] args) {
        List<Long> iidList = new ArrayList<>();
        List<Long> eventList = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        // 为什么不直接用Stream API或者直接List.foreach()？原因在于它们都要额外判断非空，否则可能引发NPE。
        ConvertUtil.foreachIfNonNull(items, item -> {
            iidList.add(item.getId());
            eventList.add(item.getEvent());
        });
        // if(items != null && items.size > 0) {
        //     for(Item item : items){
        //         iidList.add(item.getId());
        //         eventList.add(item.getEvent());
        //     }
        // }

        Map<String, Person> stringPersonMap = listToMap(list, Person::getName);
        System.out.println(stringPersonMap);


        Map<String, Person> integerPersonMap = listToMap(list, Person::getAddress, person -> person.getAge() > 18);
        System.out.println(integerPersonMap);

    }

    private static List<Person> list;

    static {
        list = new ArrayList<>();
        list.add(new Person("i", 18, "杭州", 999.9));
        list.add(new Person("am", 19, "温州", 777.7));
        list.add(new Person("iron", 21, "杭州", 888.8));
        list.add(new Person("man", 17, "宁波", 888.8));
    }

}

@Data
class Item{
    private Long id;
    private Long event;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {
    private String name;
    private Integer age;
    private String address;
    private Double salary;
}
