package muyi.base.demo;

import java.util.*;

/**
 * @author: Yang Fan
 * @date: 2020/1/6
 * @desc:
 */
public class Interview {


    // 1. Iterator 实现

    public void iteratorImpl() {
        ArrayList list = new ArrayList();
        list.iterator();
    }

    static class Person implements Cloneable {
        private String name;
        private int age;
        private int gender;

        @Override
        protected Person clone() throws CloneNotSupportedException {
            Object o = super.clone();
            Person clone = (Person) o;
            clone.age = 1;
            return clone;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", gender=" + gender +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        Person a = new Person();
        a.name = "xiaoming";
        a.age = 18;
        System.out.println(a);

        Person clone = a.clone();
        System.out.println(clone);
    }


//    /**
//     * @see HashMap
//     * @param <K>
//     * @param <V>
//     */
//    static class MyHashMap<K,V> implements Map<K, V> {
//        final static float FACTOR = 0.75f;
//
//        transient int cap;
//        transient int size;
//
//        transient Entry[] entries;
//
//        class Entry<K, V> {
//            final int hash;
//            final K key;
//            V value;
//            Entry<K,V> next;
//
//            public Entry(int hash, K key) {
//                this.hash = hash;
//                this.key = key;
//            }
//        }
//
//
//        @Override
//        public int size() {
//            return size;
//        }
//
//        @Override
//        public boolean isEmpty() {
//            return size > 0;
//        }
//
//        @Override
//        public boolean containsKey(Object key) {
//            return false;
//        }
//
//        @Override
//        public boolean containsValue(Object value) {
//            return false;
//        }
//
//        @Override
//        public V get(Object key) {
//            return null;
//        }
//
//        @Override
//        public V put(K key, V value) {
//            return null;
//        }
//
//        @Override
//        public V remove(Object key) {
//            return null;
//        }
//
//        @Override
//        public void putAll(Map<? extends K, ? extends V> m) {
//
//        }
//
//        @Override
//        public void clear() {
//
//        }
//
//        @Override
//        public Set<K> keySet() {
//            return null;
//        }
//
//        @Override
//        public Collection<V> values() {
//            return null;
//        }
//
//        @Override
//        public Set<Entry<K, V>> entrySet() {
//            return null;
//        }
//    }


}
