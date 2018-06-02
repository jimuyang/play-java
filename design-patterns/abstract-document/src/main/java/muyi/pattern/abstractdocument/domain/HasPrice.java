package muyi.pattern.abstractdocument.domain;

import muyi.pattern.abstractdocument.Document;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/6/2 上午10:46
 * @description:
 */
public interface HasPrice extends Document {
    String PROPERTY = "price";

    default Optional<Number> getPrice() {
        return Optional.ofNullable((Number) get(PROPERTY));
    }

}
