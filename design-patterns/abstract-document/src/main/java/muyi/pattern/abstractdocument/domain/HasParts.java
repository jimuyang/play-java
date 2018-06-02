package muyi.pattern.abstractdocument.domain;

import muyi.pattern.abstractdocument.Document;

import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/5/31 下午10:48
 * @description:
 */
public interface HasParts extends Document {
    String PROPERTY = "parts";

    default Stream<Part> getParts() {
        return children(PROPERTY, Part::new);
    }
}
