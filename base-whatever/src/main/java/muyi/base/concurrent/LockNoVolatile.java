package muyi.base.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Yang Fan
 * @date: 2019-07-31
 * @desc:
 */
public class LockNoVolatile {

    static class Runner implements Runnable {
        static int inner = 0;

        @Override
        public void run() {
            inner++;
        }
    }

    static class AtomicRunner implements Runnable {
        static AtomicInteger inner = new AtomicInteger(0);

        @Override
        public void run() {
            inner.getAndIncrement();
        }
    }

    static class LockRunner implements Runnable {
        static volatile int inner = 0;
        static Lock lock = new ReentrantLock();

        @Override
        public void run() {
            lock.lock();
            inner++;
            lock.unlock();
        }
    }

    static class SyncRunner implements Runnable {
        static int inner = 0;

        @Override
        public void run() {
            synchronized (SyncRunner.class) {
                inner++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 10000; i++) {
            service.submit(new AtomicRunner());
        }
        Thread.sleep(2000);
        System.out.println(AtomicRunner.inner.get());
    }


}
