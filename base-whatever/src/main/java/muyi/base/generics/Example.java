package muyi.base.generics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Yang Fan
 * @date: 2018/8/17
 * @desc:
 */
public class Example {

    public <T> void print(T... args) {
        for (T t : args) {
            System.out.println(t);
        }
    }

    @Test
    public void test() {
        print("135", 1, 56, true, Arrays.asList(1, 2), 56L);

        List<String> stringList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();

        Class clazz1 = stringList.getClass();
        Class clazz2 = integerList.getClass();

        System.out.println(clazz1);
        System.out.println(clazz2);
        System.out.println(clazz2 == clazz1);
    }

    class Fruit {

    }

    class Apple extends Fruit {

    }

    class Banana extends Fruit {

    }

    @Test
    public void testExtendAndSuper() {
        List<? extends Fruit> fruits = new ArrayList<>();

        /**
         * Error:(53, 15) java: 对于add(muyi.base.generics.Example.Apple), 找不到合适的方法
         *     方法 java.util.Collection.add(capture#1, 共 ? extends muyi.base.generics.Example.Fruit)不适用
         *       (参数不匹配; muyi.base.generics.Example.Apple无法转换为capture#1, 共 ? extends muyi.base.generics.Example.Fruit)
         */
        // 编译器无法知道这是一个appleList bananaList 还是fruitList
        // 所以往里放入任何水果是不合适的
        // 但是拿出一个，必然是水果
//        fruits.add(new Apple());
//        fruits.add(new Fruit());

        List<? super Apple> objects = new ArrayList<>();
        // 编译器无法知道这是一个objectList fruitList 还是一个appleList
        // 所以取出来只能说是个object
        // 但是放入一个苹果是一定ok的
        Object object = objects.get(0);
//        Fruit fruit = objects.get(0);
        objects.add(new Apple());

    }




}
