package muyi.pattern.abstractdocument.domain;

import muyi.pattern.abstractdocument.Document;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/6/2 上午10:48
 * @description:
 */
public interface HasType extends Document {
    String PROPERTY = "type";

    default Optional<String> getType() {
        return Optional.ofNullable((String) get(PROPERTY));
    }


}
