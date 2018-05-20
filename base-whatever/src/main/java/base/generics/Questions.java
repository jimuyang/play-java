package base.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/5/20 下午2:20
 * @description:
 */
public class Questions {

    // java中不允许创建泛型数组
    public void test() {
        // generic array creation
        // List<Integer>[] arrayOfLists = new List<Integer>[2];

        // 由于运行时期类型信息已经被擦除，
        // JVM实际上根本就不知道new ArrayList<String>()和new ArrayList<Integer>()的区别
    }


    /**
     * java 泛型的类型擦除
     *
     */
    public static void main(String[] args) {
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2); // true
        System.out.println(c1.toGenericString()); //public class java.util.ArrayList<E>
        System.out.println(c1.toString()); //class java.util.ArrayList
    }


    public class Node<T> {
        public T data;
        public Node(T data) { this.data = data; }
        public void setData(T data) {
            System.out.println("Node.setData");
            this.data = data;
        }
    }
    public class MyNode extends Node<Integer> {
        public MyNode(Integer data) { super(data); }
        public void setData(Integer data) {
            System.out.println("MyNode.setData");
            super.setData(data);
        }
    }


    // Type parameter cannot be instantiated directly
    public static <E> void append(List<E> list) {
//        E elem = new E();  // compile-time error
//        list.add(elem);
    }

    public static <E> void append(List<E> list, Class<E> cls) throws Exception {
        E elem = cls.newInstance();   // OK
        list.add(elem);
    }




}
