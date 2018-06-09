package muyi.eight.tutorial;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/6/2 下午1:30
 * @description:
 */
public class FunctionalInterface {

    /**
     * A so called functional interface must contain exactly one abstract method declaration
     */
    @java.lang.FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }

    public void test() {
        Converter<String, Integer> converter = Integer::parseInt;
        Converter<String, Integer> converter1 = Integer::valueOf;

        assert converter.convert("1234") == 1234;
        assert converter1.convert("1234") == 1234;
    }

}
