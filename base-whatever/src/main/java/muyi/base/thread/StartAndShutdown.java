package muyi.base.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author: Yang Fan
 * @date: 2019-09-02
 * @desc: 线程启动和终止
 * @see Thread
 */
public class StartAndShutdown {

    /**
     * 在运行线程之前要先构造一个线程对象，线程对象在构造时需要提供线程所需要的属性：线程组、优先级、是否daemon等等
     * 新构造的线程对象是由其parent线程进行空间分配的，
     * 也同样分配在堆内存中
     */

    // 启动线程 thread.start();
    // 含义是：parent线程告诉jvm：只要线程规划器空闲，应该立即启动被调用start方法的线程


    /**
     * 关于中断和InterruptException 几个点
     * 1. 调用thread.interrupt()[这是一个native方法]只是设置了thread的中断标识位为true [并让blocker也中断]
     * 2. 只是设置中断标识位并不会让线程停止运行[实际上不会发生任何事]
     * 即不能通过调用interrupt()来停止一个while(true)的线程 设计成这样的目的是需要开发者自己来监听这个标识位并做处理
     * 3. 怎么监听呢? 观察其他线程的标识位使用：thead.isInterrupted()
     * 线程观察自身的标识位使用：Thread.interrupted() [或者同上使用 currentThread().isInterrupted()
     * 这2者有很大的区别：是否复原自身线程的中断标识位
     * 4. 为啥Thread.interrupted()要清除自身的中断标识位呢？
     * 正是因为这是一个标识位，因此一个线程运行中调用Thread.interrupted()来试图感知自己是否有被其他线程中断时[if (Thread.interrupted()) ]
     * Java设计者认为你已经收到了标识，并有理由和义务对这个状况（被其他线程中断）进行响应和处理，此时复原标识位其实也是一种信号:我已经响应过这个中断了
     * 5. 线程面对自身被人中断的状况时，当然可以选择忽略[那又何必监听这个标识呢？], 当然最常见也是最符合语义的响应是：停止自身的执行
     * 6. 如何停止自身的执行呢？最常见的是 throw InterruptedException() 抛出一个runtime异常
     * 7. 这种停止方式常见到以至于可以这么反推：凡是一个方法抛出了InterruptedException，内部一定是因为调用了Thread.interrupted()，
     * 因此这个线程的isInterrupted()一定会返回false[标识位已经被复位]，哪怕是native方法也一样
     * <p>
     * doc: InterruptedException: The <i>interrupted status</i> of the current thread is cleared when this exception is thrown.
     * <p>
     * if (Thread.interrupted()) {
     * throw new InterruptedException();
     * }
     *
     * @see Thread#interrupt()
     * @see Thread#isInterrupted()
     * @see Thread#interrupted()
     * @see InterruptedException
     */

    /**
     * 过期的      suspend() resume() stop()
     * 对应CD机的   暂停       恢复     停止
     *
     * 不建议使用的原因有：
     * suspend()让线程进入睡眠状态时，不会释放已经占有的资源（锁）
     * stop()终结线程时 会立刻停止运行但无法保证线程的资源*正常*释放(会释放所有的资源
     * 下面有安全的终止线程的方法 而暂停和恢复可以用等待/通知机制来代替
     * @see Thread#suspend()
     * @see Thread#resume()
     * @see Thread#stop()
     */

    /**
     * 安全的终止线程
     */
    public static class Shutdown {

        public static void main(String[] args) throws Exception {
            Runner one = new Runner();
            Thread countThread = new Thread(one, "CountThread");
            countThread.start();
            // 1s后 主线程对CountThread进行中断操作
            TimeUnit.SECONDS.sleep(1);
            countThread.interrupt();

            Runner two = new Runner();
            countThread = new Thread(two, "CountThread");
            countThread.start();
            // 1s后 主线程cancel CountThread
            TimeUnit.SECONDS.sleep(1);
            two.cancel();

            // 经过多次运行 一个不太负责的结论是：
            // 使用interrupt时 线程反应要更快 毕竟interrupt0()是native方法
        }

        private static class Runner implements Runnable {
            private long i;
            // 这里我理解这个volatile在此处意义不大
            private volatile boolean on = true;

            @Override
            public void run() {
                while (on && !Thread.currentThread().isInterrupted()) {
                    i++;
                }
                System.out.println("Count i = " + i);
            }

            public void cancel() {
                on = false;
            }
        }

    }

}
