package muyi.acyclic.visitor;

/**
 * 调制解调器基类，这里规定可以accept一个解调器驱动
 *
 * @author: Jimu Yang.
 */
public abstract class Modem {

    public abstract void accept(ModemVisitor visitor);
}
