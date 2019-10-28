package muyi.base.java8;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Supplier;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/6/9 下午1:31
 * @description:
 */
public class M {

    /**
     * 判断对象是否为null 是则抛出NullPointerException
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T requireNonNull(T t) {
        return Objects.requireNonNull(t);
    }


    /**
     * 自定义的判断为空方法
     *
     * @param object
     * @return
     */
    public static boolean isNull(Object object) {
        if (object == null)
            return true;
        if (object instanceof String)
            return object.equals("");
        if (object instanceof Collection)
            return ((Collection) object).size() == 0;
        if (object instanceof Map)
            return ((Map) object).isEmpty();
        //暂时没有好的方法判断 array
//        if (object instanceof Object[]) {
//            return ((Object[]) object).length == 0;
//        }
        return false;
    }

    public static boolean nonNull(Object object) {
        return !isNull(object);
    }

    /**
     * convenient print
     *
     * @param object
     */
    public static void print(Object object) {
        System.out.println(object);
    }


    /**
     * @param resolver
     * @param <T>
     * @return
     */
    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
