package muyi.aggregator.dto;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
public class Product {

    private int inventory;

    private String title;

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
