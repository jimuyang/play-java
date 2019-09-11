package muyi.base.concurrent;

/**
 * @author: Yang Fan
 * @date: 2019-09-09
 * @desc:
 * @see java.util.concurrent.locks.AbstractQueuedSynchronizer 队列同步器
 */
public class AQSLearn {

    /**
     * 我的理解是：参考锁竞争机制，需要一个阻塞对立（FIFO）来持有多个前来竞争的线程，AQS就提供了这样一个队列
     *
     * 哪些用了AQS：
     * @see java.util.concurrent.FutureTask 之前的版本
     *      mainly to avoid surprising users about retaining interrupt status during cancellation races.
     * @see java.util.concurrent.CountDownLatch 最标准的使用姿势
     * @see java.util.concurrent.Semaphore 最标准的使用姿势
     *
     * @see java.util.concurrent.CyclicBarrier 这没有使用AQS 放在这里只是提醒
     */


}
