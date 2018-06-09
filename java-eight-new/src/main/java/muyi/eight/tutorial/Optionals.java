package muyi.eight.tutorial;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/6/3 下午2:44
 * @description:
 */
public class Optionals {

    /**
     * 关于Optional
     * @see OptionalDemo
     */
    void test() {
        String str = null;
        str = "bra";
        Optional<String> stringOptional = Optional.of(str);
        assert stringOptional.isPresent();
        assert stringOptional.get().equals(str);
        stringOptional.orElse("other");

        stringOptional.ifPresent(System.out::println);

    }
}
