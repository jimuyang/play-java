package muyi.acyclic.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Zoom牌调制解调器，仅支持zoom驱动
 *
 * @author: Jimu Yang.
 */
public class Zoom extends Modem {
    private static final Logger LOGGER = LoggerFactory.getLogger(Zoom.class);

    @Override
    public void accept(ModemVisitor visitor) {
        try {
            ((ZoomVisitor) visitor).visit(this);
        } catch (ClassCastException e) {
            LOGGER.error("Unable to cast to Zoom Visitor");
        }
    }

    @Override
    public String toString() {
        return "Zoom modem";
    }
}
