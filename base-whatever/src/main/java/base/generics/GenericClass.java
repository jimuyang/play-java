package base.generics;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/5/19 下午6:35
 * @description: 泛型class
 */
public class GenericClass {

    public class Box {
        private String object;

        public String getObject() {
            return object;
        }

        public void setObject(String object) {
            this.object = object;
        }

    }

    public class BoxT<T> {
        private T t;

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }

        public void example() {
            BoxT<Integer> integerBox = new BoxT<Integer>();
            BoxT<String> stringBox = new BoxT<String>();

//            this.test(integerBox);
            // 虽然Integer和Double是Number的子类，
            // 但是在泛型中Box<Integer>或者Box<Double>与Box<Number>之间并没有任何的关系
        }

        public void test(BoxT<Number> numberBoxT) {

        }


    }
}
