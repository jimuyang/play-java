package muyi.acyclic.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DOS系统实现了所有品牌的调制解调器驱动，可使用所有的调制解调器
 *
 * @author: Jimu Yang.
 */
public class ConfigureForDosVisitor implements AllModemVistor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigureForDosVisitor.class);

    @Override
    public void visit(Hayes hayes) {
        LOGGER.info(hayes + " used with Dos configurator.");
    }

    @Override
    public void visit(Zoom zoom) {
        LOGGER.info(zoom + " used with Dos configurator.");
    }
}
