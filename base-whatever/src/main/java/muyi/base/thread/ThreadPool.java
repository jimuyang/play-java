package muyi.base.thread;

/**
 * @author: Yang Fan
 * @date: 2019-09-03
 * @desc: 简单的线程池定义
 */
public interface ThreadPool<Job extends Runnable> {

    /**
     * 执行一个job
     */
    void execute(Job job);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 增加工作者线程
     */
    void addWorkers(int num);

    /**
     * 减少工作者线程
     */
    void removeWorkers(int num);

    /**
     * 正在等待执行的job数量
     */
    int countWaitJobs();

}
