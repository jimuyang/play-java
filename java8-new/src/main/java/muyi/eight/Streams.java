package muyi.eight;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/6/3 下午5:14
 * @description:
 */
public class Streams {
    /**
     * A java.util.Stream represents a sequence of elements on which one or more operations can be performed.
     * Stream operations are either intermediate or terminal.
     */

    /**
     * like a query
     * 就像是在 数据库里执行select语句 无论怎么查询 不应该影响到数据本来的样子
     */

    void main() {
//        Stream stream = Arrays.asList("a", "b", "c").stream();
        Stream stream = Stream.of("a", "b", "c");

        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        stringCollection.stream()
                .filter(s -> s.startsWith("a"))
                .forEach(System.out::println);
        System.out.println("------------");

        stringCollection.stream()
                .sorted()
                .filter(s -> s.startsWith("a"))
                .forEach(System.out::println);
        System.out.println("------------");


        stringCollection.stream()
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
        System.out.println("------------");


        /* match */
        boolean anyStartsWithA = stringCollection.stream().anyMatch(s -> s.startsWith("a"));
        assert anyStartsWithA;

        boolean allStartsWithA = stringCollection.stream().allMatch(s -> s.startsWith("a"));
        assert !allStartsWithA;

        boolean noneStartsWithZ = stringCollection.stream().noneMatch(s -> s.startsWith("z"));
        assert noneStartsWithZ;

        /* count */
        long countStartWithB = stringCollection
                .stream()
                .filter(s -> s.startsWith("b"))
                .count();
        assert countStartWithB == 3L;

        Optional<String> stringOptional = stringCollection.stream()
                .reduce((s1, s2) -> s1 + "#" + s2);
        System.out.println(stringOptional.orElse("null"));
        System.out.println("------------");

    }

    /**
     * Parallel Streams
     */
    void testParallelStream() {
        int max = 1000000;
        List<String> list = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            list.add(uuid.toString());
        }

        long t0 = System.nanoTime();
        long count = list.stream().sorted().count();
        assert count == max;
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));


        long t3 = System.nanoTime();
        long count1 = list.parallelStream().sorted().count();
        assert count1 == max;
        long t4 = System.nanoTime();

        millis = TimeUnit.NANOSECONDS.toMillis(t4 - t3);
        System.out.println(String.format("parallel sort took: %d ms", millis));
    }

    /* maps */
    void testMaps() {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }
        map.forEach((id, val) -> System.out.println(val));

        map.keySet().stream();
        map.values().stream();
        map.entrySet().stream();

        map.computeIfPresent(3, (key, value) -> value + key);
        assert "val33".equals(map.get(3));

        map.computeIfPresent(9, (key, value) -> null);
        assert !map.containsKey(9);
        assert map.get(11) == null;

        map.computeIfAbsent(12, key -> "val" + key);
        assert map.containsKey(12);

        map.computeIfAbsent(3, key -> "null");
        assert "val33".equals(map.get(3));

        map.forEach((id, val) -> System.out.println(val));

        map.getOrDefault(14, "not found");

        map.merge(9, "val9", String::concat);
        map.get(9);             // val9

        map.merge(9, "concat", String::concat);
        map.get(9);             // val9concat

        //可以用于改写 map putOrAdd方法
    }
    

}
