package muyi.base.thread;


import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: Yang Fan
 * @date: 2019-09-03
 * @desc:
 */
public class SimpleThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    /**
     * 线程池最大限制数
     */
    private static final int MAX_WORKER_COUNT = 10;

    /**
     * 线程池默认数量
     */
    private static final int DEFAULT_WORKER_COUNT = 5;

    /**
     * 线程池最小数量
     */
    private static final int MIN_WORKER_COUNT = 1;

    /**
     * 工作列表
     */
    private final LinkedList<Job> jobs = new LinkedList<>();

    /**
     * 工作线程
     */
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<>());

    /**
     * 工作线程的数量
     */
    private int workerNum = DEFAULT_WORKER_COUNT;

    /**
     * 线程编号生成
     */
    private AtomicLong threadNum = new AtomicLong();

    public SimpleThreadPool() {
        initializeWorkers(DEFAULT_WORKER_COUNT);
    }

    public SimpleThreadPool(int workerNum) {
        workerNum = workerNum > MAX_WORKER_COUNT ? MAX_WORKER_COUNT :
                workerNum < MIN_WORKER_COUNT ? MIN_WORKER_COUNT : workerNum;
        initializeWorkers(workerNum);
    }


    /**
     * 初始化线程工作者
     */
    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    @Override
    public void execute(Job job) {
        if (job != null) {
            synchronized (jobs) {
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        // ??? 为啥这里要加锁jobs
        synchronized (jobs) {
            if (num + workerNum > MAX_WORKER_COUNT) {
                num = MAX_WORKER_COUNT - workerNum;
            }
            initializeWorkers(num);
            workerNum += num;
        }
    }

    @Override
    public void removeWorkers(int num) {
        // todo
    }

    @Override
    public int countWaitJobs() {
        return jobs.size();
    }


    /**
     * 工作者 负责消费任务
     */
    class Worker implements Runnable {
        // 是否工作
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job = null;
                // 上锁时间较短
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
                        // 忽略Job执行中的异常
                    }
                }
            }
        }

        public void shutdown() {
            running = false;
        }
    }
}
