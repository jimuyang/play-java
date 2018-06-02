package muyi.eight;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/6/2 下午8:35
 * @description:
 */
public class MethodReference {
    /**
     * Java 8 enables you to pass references of methods or constructors via the :: keyword.
     * The above example shows how to reference a static method.
     * But we can also reference object methods:
     */
    class Something {
        String startsWith(String s) {
            Objects.requireNonNull(s);
            return String.valueOf(s.charAt(0));
        }
    }

    void test() {
        Something something = new Something();
        FunctionalInterface.Converter<String, String> converter = something::startsWith;
        String converted = converter.convert("Java");
        assert converted.equals("J");
    }

    class Person {
        String firstName;
        String lastName;

        Person() {}

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return firstName + " " + lastName;
        }
    }

    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }

    void test1() {
        PersonFactory<Person> personFactory = Person::new;
        Person king = personFactory.create("LeBron", "James");
        assert king.toString().equals("LeBron James");
    }

    void test2() {
        int num = 1;
        FunctionalInterface.Converter<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num);
        assert stringConverter.convert(2).equals("3");
    }

    static class Lambda4 {
        static int outerStaticNum;
        int outerNum;

        void testScopes() {
            FunctionalInterface.Converter<Integer, String> stringConverter1 = (from) -> {
                outerNum = 23;
                return String.valueOf(from);
            };
            stringConverter1.convert(1);
            FunctionalInterface.Converter<Integer, String> stringConverter2 = (from) -> {
                outerStaticNum = 72;
                return String.valueOf(from);
            };
            stringConverter2.convert(1);
        }
    }

    void test3() {
        Lambda4 lambda4 = new Lambda4();
        lambda4.testScopes();
        assert lambda4.outerNum == 23;
        assert Lambda4.outerStaticNum == 72;
    }

    interface Formula {
        double calculate(int a);

        default double sqrt(int a) {
            return Math.sqrt(a);
        }
    }

    void test4() {
        //下面这种写法是不可以的
        //Formula formula = a -> sqrt(a* 100);
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
    }


}
