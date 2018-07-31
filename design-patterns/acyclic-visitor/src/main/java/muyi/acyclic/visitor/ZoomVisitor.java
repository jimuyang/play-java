package muyi.acyclic.visitor;

/**
 * Zoom专用驱动，可以访问Zoom牌调制解调器
 *
 * @author: Jimu Yang.
 */
public interface ZoomVisitor extends ModemVisitor{
    void visit(Zoom zoom);
}
