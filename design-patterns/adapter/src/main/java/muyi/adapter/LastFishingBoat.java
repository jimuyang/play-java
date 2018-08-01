package muyi.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
public class LastFishingBoat implements FishingBoat {
    private static final Logger LOGGER = LoggerFactory.getLogger(LastFishingBoat.class);

    @Override
    public void sail() {
        LOGGER.info("The Last FishingBoat is sailing.");
    }
}
