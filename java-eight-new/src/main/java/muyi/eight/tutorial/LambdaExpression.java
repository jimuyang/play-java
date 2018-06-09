package muyi.eight.tutorial;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/6/2 下午1:14
 * @description:
 */
public class LambdaExpression {


    public void oldSort() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "jim");

        //注意 倒序排序
        names.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        names.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }

    public void sortWithLambda() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "jim");
        names.sort((o1, o2) -> o2.compareTo(o1));
        names.forEach(System.out::println);
    }
}
