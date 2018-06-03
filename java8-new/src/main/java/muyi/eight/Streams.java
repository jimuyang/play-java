package muyi.eight;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        stringCollection.stream()
                .sorted()
                .filter(s -> s.startsWith("a"))
                .forEach(System.out::println);

        stringCollection.stream()
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        /* match */
        boolean anyStartsWithA = stringCollection.stream().anyMatch(s -> s.startsWith("a"));
        assert anyStartsWithA;

        boolean allStartsWithA = stringCollection.stream().allMatch(s -> s.startsWith("a"));
        assert !allStartsWithA;

        boolean noneStartsWithZ = stringCollection.stream().noneMatch(s -> s.startsWith("z"));
        assert noneStartsWithZ;
        

    }

}
