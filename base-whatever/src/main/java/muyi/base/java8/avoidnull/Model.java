package muyi.base.java8.avoidnull;

import muyi.base.java8.M;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/6/9 上午11:13
 * @description:
 */
public class Model {


    class Outer {
        Nested nested;

        public Nested getNested() {
            return nested;
        }
    }

    class Nested {
        Inner inner;

        public Inner getInner() {
            return inner;
        }
    }

    class Inner {
        String name;

        public String getName() {
            return name;
        }
    }


    void test() {
        Outer outer1 = new Outer();
        if (M.nonNull(outer1) && M.nonNull(outer1.nested) && M.nonNull(outer1.nested.inner)) {
            M.print(outer1.getNested().getInner().getName());
        }

        Optional optionalOuter = Optional.of(new Outer());
        // non-static method cannot be referenced by static context.
        // optionalOuter.map(Outer::getNested);


        Optional.of(new Outer())
                .map(Outer::getNested)
                .map(Nested::getInner)
                .map(Inner::getName)
                .ifPresent(M::print);

        Outer outer2 = new Outer();
        Optional<String> opString = M.resolve(() -> outer2.getNested().getInner().getName());
        M.print(opString.orElse("null string"));


        //try-catch
        String name;
        try {
            name = outer2.getNested().getInner().getName();
        }catch (NullPointerException npe) {
            name = "null string";
        }
        M.print(name);
    }
}
