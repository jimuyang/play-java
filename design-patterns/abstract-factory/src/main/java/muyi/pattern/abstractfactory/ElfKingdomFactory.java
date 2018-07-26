package muyi.pattern.abstractfactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
public class ElfKingdomFactory implements KingdomFactory {

    private static final String DESCRIPTION = "This is Elven Kingdom ";

    @Override
    public Castle createCastle() {
        return () -> DESCRIPTION + "castle.";
    }

    @Override
    public Army createArmy() {
        return () -> DESCRIPTION + "army.";
    }

    @Override
    public King createKing() {
        return () -> DESCRIPTION + "king.";
    }
}
