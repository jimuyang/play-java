package muyi.base.generics;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/5/19 下午6:35
 * @description: 泛型 method
 * <p>
 * inner class cannot have static declarations
 */
public class GenericMethod {

    public static class Util {
        public static <K, V> boolean pairEquals(Pair<K, V> p1, Pair<K, V> p2) {
            return p1.getKey().equals(p2.getKey()) &&
                    p1.getValue().equals(p2.getValue());
        }


        public static <T extends Comparable<T>> int countGreaterThan(T[] array, T item) {
            int count = 0;
            for (T t : array) {
                if (t.compareTo(item) > 0) {
                    count++;
                }
            }
            return count;
        }
    }

    public class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public boolean equals(Pair<K, V> obj) {
            return Util.pairEquals(this, obj);
        }


    }

}
