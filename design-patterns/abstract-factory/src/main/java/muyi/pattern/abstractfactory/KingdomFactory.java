package muyi.pattern.abstractfactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
public interface KingdomFactory {
    Castle createCastle();
    Army createArmy();
    King createKing();
}
