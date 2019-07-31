package muyi.base.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Yang Fan
 * @date: 2019-07-31
 * @desc: 复现不出来
 */
public class VolatileHappensBefore {

    static boolean flag = false;

    static int state1 = 0;

    static class Changer implements Runnable {
        @Override
        public void run() {
            state1 = 1;
            flag = true;
        }
    }

    static class Reader implements Runnable {
        @Override
        public void run() {
            if (flag) {
                if (state1 != 1) {
                    System.out.println("不好");
                }
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10000; i++) {
            service.submit(new Reader());
        }
        service.submit(new Changer());
        for (int i = 0; i < 10000; i++) {
            service.submit(new Reader());
        }
    }

}
