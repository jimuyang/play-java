package muyi.adapter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
public class Captain {

    private RowingBoat rowingBoat;

    public Captain(){
    }

    public Captain(RowingBoat rowingBoat) {
        this.rowingBoat = rowingBoat;
    }

    public void setRowingBoat(RowingBoat rowingBoat) {
        this.rowingBoat = rowingBoat;
    }

    public void row() {
        this.rowingBoat.row();
    }
}
