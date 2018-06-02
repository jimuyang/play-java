package muyi.base.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/5/20 下午12:24
 * @description:  Producer Extends, Consumer Super
 */
public class GenericExtend {

    static class Fruit {
    }

    static class Apple extends Fruit {
    }

    static class Orange extends Fruit {
    }

    static class GenericEating {
        static List<Apple> apples = new ArrayList<Apple>();
        static List<Fruit> fruits = new ArrayList<Fruit>();

        static {
            apples.add(new Apple());
            fruits.add(new Fruit());
        }

        static class Eater<T> {
            T eatOne(List<T> list) {
                return list.get(0);
            }
        }

        static void testEat() {
            // Errors: List<Fruit> cannot be applied to List<Apple>.
//            new Eater<Fruit>().eatOne(apples);

            new BetterEater<Fruit>().eatOne(apples);

            /**
             * extends 可以get 不能add
             */
            List<? extends Fruit> fruits = new ArrayList<Apple>();
            //Apple apple = fruits.get(0); 不可 因为这可能是一个 orange list
            Fruit fruit = fruits.get(0);
            Object object = fruits.get(0);
            // 下面都不行
            // fruits.add(new Apple());
            // fruits.add(new Fruit());
            // fruits.add(new Orange());
            // 所以对于实现了<? extends T>的集合类只能将它视为Producer向外提供(get)元素，
            // 而不能作为Consumer来对外获取(add)元素

            /**
             * super 可以add 不能get
             */
            List<? super Fruit> fruits1 = new ArrayList<Object>();
            fruits1.add(new Apple());
            fruits1.add(new Fruit());
            // fruits1.add(new Object()); 不可 因为这可能是一个fruit list
        }


        static class BetterEater<T> {
            T eatOne(List<? extends T> list) {
                return list.get(0);
            }
        }

        /**
         * extend super 一起用
         */

        public static class Collections {
            public static <T> void copy(List<? super T> dest, List<? extends T> src) {
                for (int i=0; i<src.size(); i++)
                    dest.set(i, src.get(i));
            }
        }

    }


}
