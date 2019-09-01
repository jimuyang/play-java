package muyi.base.util;

import java.util.concurrent.TimeUnit;

/**
 * @author: Yang Fan
 * @date: 2019-09-02
 * @desc:
 */
public class SleepUtil {

    public static void sleepSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException ignore) {
        }
    }
}
