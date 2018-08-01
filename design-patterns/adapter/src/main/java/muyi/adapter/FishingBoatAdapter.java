package muyi.adapter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
public class FishingBoatAdapter implements RowingBoat {


    private FishingBoat fishingBoat;

    public FishingBoatAdapter(FishingBoat fishingBoat) {
        this.fishingBoat = fishingBoat;
    }

    @Override
    public void row() {
        this.fishingBoat.sail();
    }
}
