package base.java.eight;


import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/5/27 上午12:12
 * @description:
 */
public class OptionalDemoTest {

    @Test
    public void createOptionalObjects() {
        Optional<String> opString = Optional.empty();
        Assert.assertFalse(opString.isPresent());

        String name = "muyi";
        Optional<String> opt = Optional.of(name);
        Assert.assertEquals("Optional[muyi]", opt.toString());

    }

    @Test(expected = NullPointerException.class)
    public void createOptionalObjectsWhileNull() {
        String name = null;
        Optional<String> opt = Optional.of(name);
    }

    @Test
    public void createOptionalObjectsUseOfNullable() {
        String name = null;
        Optional<String> opt = Optional.ofNullable(name);
        Assert.assertEquals("Optional.empty", opt.toString());
    }

    @Test
    public void testIsPresent() {
        Optional<String> opt = Optional.of("muyi");
        Assert.assertTrue(opt.isPresent());

        opt = Optional.ofNullable(null);
        Assert.assertFalse(opt.isPresent());
    }

    @Test
    public void testUseIfPresent() {
        Optional<String> opt = Optional.of("muyi");
        opt.ifPresent(name -> System.out.print(name));
    }

    @Test
    public void testOrElse() {
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElse("muyi");
        Assert.assertEquals("muyi", name);
    }

    @Test
    public void testOrElseGet() {
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElseGet(() -> "muyi");
        Assert.assertEquals("muyi", name);
    }


    public String getMyDefault() {
        System.out.println("Getting default value...");
        return "muyi";
    }

    /**
     * orElse orElseGet 的区别
     */
    @Test
    public void compareOrElseAndOrElseGet() {
        System.out.println("When text is null......");
        String text = null;

        System.out.println("Using orElseGet()");
        String defaultText = Optional.ofNullable(text).orElseGet(this::getMyDefault);
        Assert.assertEquals("muyi", defaultText);

        System.out.println("Using orElse()");
        defaultText = Optional.ofNullable(text).orElse(this.getMyDefault());
        Assert.assertEquals("muyi", defaultText);

        System.out.println("When text is present......");
        text = "hello";

        System.out.println("Using orElseGet()");
        defaultText = Optional.ofNullable(text).orElseGet(this::getMyDefault);
        Assert.assertEquals("hello", defaultText);

        System.out.println("Using orElse()");
        defaultText = Optional.ofNullable(text).orElse(this.getMyDefault());
        Assert.assertEquals("hello", defaultText);
    }

    @Test(expected = IllegalAccessException.class)
    public void testOrElseThrow() throws Exception {
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElseThrow(IllegalAccessException::new);
    }

    @Test
    public void testGet() {
        Optional<String> opt = Optional.of("muyi");
        String name = opt.get();
        Assert.assertEquals("muyi", name);
    }


    @Test
    public void testFilter() {
        Integer year = 2018;
        Optional<Integer> yearOpt = Optional.of(year);
        boolean is2018 = yearOpt.filter(y -> y == 2018).isPresent();
        Assert.assertTrue(is2018);

        boolean is2017 = yearOpt.filter(y -> y == 2017).isPresent();
        Assert.assertFalse(is2017);

    }


    public class Modem {
        private Double price;

        public Modem(Double price) {
            this.price = price;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }

    public boolean priceIsRange(Modem modem) {
        if (modem != null && modem.getPrice() != null
                && modem.getPrice() >= 10
                && modem.getPrice() <= 15) {
            return true;
        }
        return false;
    }

    @Test
    public void testWithoutOptional() {
        Assert.assertTrue(priceIsRange(new Modem(10.0)));
        Assert.assertFalse(priceIsRange(new Modem(null)));
        Assert.assertFalse(priceIsRange(new Modem(9.9)));
        Assert.assertFalse(priceIsRange(new Modem(15.5)));
        Assert.assertFalse(priceIsRange(null));
    }


    public boolean priceIsInRangeByOptional(Modem modem) {
        return Optional.ofNullable(modem)
                .map(Modem::getPrice)
                .filter(p -> p >= 10)
                .filter(p -> p <= 15)
                .isPresent();
    }

    @Test
    public void testWithOptional() {
        Assert.assertTrue(priceIsInRangeByOptional(new Modem(10.0)));
        Assert.assertFalse(priceIsInRangeByOptional(new Modem(null)));
        Assert.assertFalse(priceIsInRangeByOptional(new Modem(9.9)));
        Assert.assertFalse(priceIsInRangeByOptional(new Modem(15.5)));
        Assert.assertFalse(priceIsInRangeByOptional(null));
    }


    @Test
    public void testMap() {
        List<String> companyNames = Arrays.asList(
                "paypal", "oracle", "", "microsoft", "", "apple"
        );
        Optional<List<String>> listOptional = Optional.of(companyNames);

        int size = listOptional.map(List::size).orElse(0);
        Assert.assertEquals(6, size);

    }

    @Test
    public void testPassword() {
        String password = "  password  ";
        Optional<String> passOpt = Optional.of(password);

        boolean isCorrect = passOpt.filter(pass -> pass.equals("password")).isPresent();
        Assert.assertFalse(isCorrect);

        isCorrect = passOpt.map(String::trim)
                .filter(pass -> pass.equals("password"))
                .isPresent();
        Assert.assertTrue(isCorrect);
    }

    public class Person {
        private String name;
        private Integer age;
        private String password;


        public Person(String name, Integer age, String password) {
            this.name = name;
            this.age = age;
            this.password = password;
        }

        public Optional<String> getName() {
            return Optional.ofNullable(name);
        }

        public Optional<Integer> getAge() {
            return Optional.ofNullable(age);
        }

        public Optional<String> getPassword() {
            return Optional.ofNullable(password);
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    @Test
    public void testFlatMap() throws Exception {
        Person person = new Person("john", 24, null);
        Optional<Person> personOptional = Optional.of(person);

        Optional<Optional<String>> nameOptionalWrapper
                = personOptional.map(Person::getName);

        Optional<String> nameOptional = nameOptionalWrapper.orElseThrow(IllegalAccessException::new);
        String name1 = nameOptional.orElse("");
        Assert.assertEquals("join", name1);

        //the provided mapper is one whose result is already an {@code Optional}
        String name = personOptional.flatMap(Person::getName).orElse("");
        Assert.assertEquals("john", name);
    }



}