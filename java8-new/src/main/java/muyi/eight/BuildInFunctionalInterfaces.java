package muyi.eight;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/6/3 下午1:40
 * @description:
 */
public class BuildInFunctionalInterfaces {
    void testPredicate() {
        Predicate<String> predicate = s -> s.length() > 0;

        assert predicate.test("foo");
        assert !predicate.negate().test("foo");
        Predicate nonNull = Objects::nonNull;
    }

    void testFunction() {
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<Integer, String> toString = String::valueOf;

        Function<String, String> str2str = toInteger.andThen(toString);
        Function<Integer, Integer> int2int = toInteger.compose(toString);

        assert "154435".equals(str2str.apply("154435"));
        assert 123514 == int2int.apply(123514);
    }

    void testSuppliers() {
        MethodReference.PersonFactory personFactory = MethodReference.Person::new;
//        Supplier<MethodReference.Person> personSupplier = personFactory::create;
        Supplier<MethodReference.Person> personSupplier = () -> personFactory.create("xiao","ming");
        Supplier<MethodReference.Person> personSupplier1 = MethodReference.Person::new;

        MethodReference.Person person = personSupplier.get();
        MethodReference.Person person1 = personSupplier1.get();

        System.out.println(person.toString());
        System.out.println(person1.toString());
    }

    void testConsumers() {
        Consumer<MethodReference.Person> greeter = (p) -> System.out.println("Hello, " + p.toString());
        greeter.accept(new MethodReference.Person("Peter", "Pan"));
    }

    void testComparators() {
        Comparator<MethodReference.Person> comparator = Comparator.comparing(MethodReference.Person::getFirstName);
//        Comparator<MethodReference.Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);

        MethodReference.Person p1 = new MethodReference.Person("xiao", "ming");
        MethodReference.Person p2 = new MethodReference.Person("da", "zhang");

        assert comparator.compare(p1, p2) > 0;
        assert comparator.reversed().compare(p1, p2) < 0;
    }

}
