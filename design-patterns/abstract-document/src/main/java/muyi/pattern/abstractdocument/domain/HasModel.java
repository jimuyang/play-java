package muyi.pattern.abstractdocument.domain;

import muyi.pattern.abstractdocument.Document;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/5/31 下午10:45
 * @description:
 */
public interface HasModel extends Document{
    String PROPERTY = "model";

    default Optional<String> getModel() {
        return Optional.ofNullable((String) get(PROPERTY));
    }
}
