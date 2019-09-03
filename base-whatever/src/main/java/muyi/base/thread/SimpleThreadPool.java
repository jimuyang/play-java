package muyi.base.thread;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: Yang Fan
 * @date: 2019-09-03
 * @desc:
 */
public class SimpleThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    // 线程池最大限制数
    private static final int MAX_WORKER_COUNT = 10;

    // 线程池默认数量
    private static final int DEFAULT_WORKER_COUNT = 5;

    // 线程池最小数量
    private static final int MIN_WORKER_COUNT = 1;

    // 工作列表
    private final LinkedList<Job> jobs = new LinkedList<>();

    // 工作线程
//    private final List<Worker> workers =


    @Override

    public void execute(Job job) {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public void addWorkers(int num) {

    }

    @Override
    public void removeWorkers(int num) {

    }

    @Override
    public int countWaitJobs() {
        return 0;
    }


    class Worker implements Runnable {
        // 是否工作
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    // 如果jobs是空的 那就wait
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException ie) {
                            // 感知到外部对worker的中断
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    // 取出一个job
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    try {
                        job.run();
                    } catch (Exception ignore) {
                    }
                }
            }
        }

        public void shutdown() {
            running = false;
        }
    }
}
