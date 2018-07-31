package muyi.acyclic.visitor;

/**
 * Hayes调制解调器专用驱动，可访问Hayes牌调制解调器
 *
 * @author: Jimu Yang.
 */
public interface HayesVisitor extends ModemVisitor{

    void visit(Hayes hayes);
}
