package muyi.acyclic.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UNIX系统仅实现了Zoom牌调制解调器驱动，仅支持该调制解调器
 *
 * @author: Jimu Yang.
 */
public class ConfigureForUnixVisitor implements ModemVisitor, ZoomVisitor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigureForUnixVisitor.class);
    @Override
    public void visit(Zoom zoom) {
        LOGGER.info(zoom + " used with Unix Configurator.");
    }
}
