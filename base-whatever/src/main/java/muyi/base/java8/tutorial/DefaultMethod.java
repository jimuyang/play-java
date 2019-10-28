package muyi.base.java8.tutorial;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/6/2 上午11:15
 * @description:
 */
public class DefaultMethod {
    interface Formula {
        double calculate(int a);

        default double sqrt(int a) {
            return Math.sqrt(a);
        }
    }

    void test() {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
        assert formula.calculate(16) == 40;
        assert formula.sqrt(16) == 4;
    }
}
