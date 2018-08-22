package muyi.ambassador;


import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
@Slf4j
public class ServiceAmbassador implements RemoteServiceInterface {

    private static final int RETRIES = 3;
    private static final int DELAY_MS = 3000;

    ServiceAmbassador() {
    }

    @Override
    public long doRemoteFunction(int value) {
        return safeCall(value);
    }

    private long checkLatency(int value) {
        long startTime = System.currentTimeMillis();
        long result = RemoteService.getRemoteService().doRemoteFunction(value);
        long timeToken = System.currentTimeMillis() - startTime;

        log.info("Time taken (ms) : " + timeToken);
        return result;
    }

    private long safeCall(int value) {
        int retries = 0;
        long result = -1;

        for (int i = 0; i < RETRIES; i++) {
            if (retries >= RETRIES) {
                return -1;
            }

            if ((result = checkLatency(value)) == -1) {
                log.info("Failed to reach remote: (" + (i + 1) + ")");
                retries++;
                try {
                    sleep(DELAY_MS);
                } catch (InterruptedException e) {
                    log.error("Thread sleep state interrupted", e);

                }
            } else {
                break;
            }

        }
        return result;
    }
}
