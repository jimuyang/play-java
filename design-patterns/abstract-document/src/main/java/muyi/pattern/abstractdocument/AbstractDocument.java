package muyi.pattern.abstractdocument;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/5/26 下午11:07
 * @description:
 */
public abstract class AbstractDocument implements Document {

    private final Map<String, Object> properties;

    protected AbstractDocument(Map<String, Object> properties) {
        //Objects是一个工具类 requireNonNull会抛出一个NullPointerException
        Objects.requireNonNull(properties, "properties is required");
        this.properties = properties;
    }

    @Override
    public Void put(String key, Object value) {
        //Void 标识的方法只能返回null
        properties.put(key, value);
        return null;
    }

    @Override
    public Object get(String key) {
        return properties.get(key);
    }


    @Override
    public <T> Stream<T> children(String key, Function<Map<String, Object>, T> constructor) {
        Optional<List<Map<String, Object>>> any = Stream.of(this.get(key)).filter(Objects::nonNull)
                .map(el -> (List<Map<String, Object>>) el).findAny();
        return any.isPresent() ? any.get().stream().map(constructor) : Stream.empty();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append("[");
        properties.forEach((key, value) -> builder.append("[").append(key).append(" : ").append(value).append("]"));
        builder.append("]");
        return builder.toString();
    }
}
