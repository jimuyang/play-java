package muyi.pattern.abstractdocument.domain;

import muyi.pattern.abstractdocument.AbstractDocument;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/5/31 下午10:44
 * @description:
 */
public class Car extends AbstractDocument implements HasParts, HasModel, HasPrice {
    public Car(Map<String, Object> properties) {
        super(properties);
    }
}
