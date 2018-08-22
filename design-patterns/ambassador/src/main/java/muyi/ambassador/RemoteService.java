package muyi.ambassador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Thread.sleep;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
public class RemoteService implements RemoteServiceInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteService.class);


    private static RemoteService remoteService = null;

    static synchronized RemoteService getRemoteService() {
        if (remoteService == null) {
            remoteService = new RemoteService();
        }
        return remoteService;
    }

    private RemoteService() { }


    @Override
    public long doRemoteFunction(int value) {

        long waitTime = (long) Math.floor(Math.random() * 1000);

        try {
            sleep(waitTime);
        } catch (InterruptedException e) {
            LOGGER.error("Thread sleep state interrupted", e);
        }
        return waitTime >= 200 ? value * 10 : -1;
    }
}
