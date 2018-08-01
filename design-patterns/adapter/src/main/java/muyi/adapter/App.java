package muyi.adapter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
public class App {


    public static void main(String[] args) {
        // the last fishing boat
        FishingBoat fishingBoat = new LastFishingBoat();

        // get a captain
        Captain captain = new Captain();

        // use the adapter
        FishingBoatAdapter adapter = new FishingBoatAdapter(fishingBoat);
        captain.setRowingBoat(adapter);

        captain.row();
    }
}
