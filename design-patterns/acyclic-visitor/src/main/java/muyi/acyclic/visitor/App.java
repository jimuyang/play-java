package muyi.acyclic.visitor;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
public class App {

    public static void main(String[] args) {

        ConfigureForUnixVisitor conUnix = new ConfigureForUnixVisitor();
        ConfigureForDosVisitor conDos = new ConfigureForDosVisitor();

        Zoom zoom = new Zoom();
        Hayes hayes = new Hayes();

        hayes.accept(conDos);
        hayes.accept(conUnix);

        zoom.accept(conDos);
        zoom.accept(conUnix);
    }
}
