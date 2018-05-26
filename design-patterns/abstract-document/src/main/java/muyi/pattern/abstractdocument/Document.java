package muyi.pattern.abstractdocument;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/5/26 下午11:09
 * @description:
 */
public interface Document {

    /**
     * set the key value
     *
     * @param key
     * @param value
     * @return
     */
    Void put(String key, Object value);

    /**
     * get the value by key
     *
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * Gets the stream of the child documents
     *
     * @param key  element key
     * @param constructor  constructor of the child class
     * @param <T>
     * @return child documents
     */
    <T> Stream<T> children(String key, Function<Map<String, Object>, T> constructor);

}
