package muyi.acyclic.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hayes牌调制解调器，仅支持专用驱动
 *
 * @author: Jimu Yang.
 */
public class Hayes extends Modem {
    private static final Logger LOGGER = LoggerFactory.getLogger(Hayes.class);
    /**
     * Accepts all visitors but honors only HayesVisitor
     * @param visitor
     */
    @Override
    public void accept(ModemVisitor visitor) {
        try {
            ((HayesVisitor) visitor).visit(this);
        }catch (ClassCastException e) {
            LOGGER.error("Unable to cast to HayesVisitor");
        }
    }

    @Override
    public String toString() {
        return "Hayes modem";
    }
}
