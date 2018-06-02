package muyi.pattern.abstractdocument.domain;

import muyi.pattern.abstractdocument.AbstractDocument;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/6/2 上午10:44
 * @description: Part entity
 */
public class Part extends AbstractDocument implements HasType, HasPrice, HasModel{

    public Part(Map<String, Object> properties) {
        super(properties);
    }

}
