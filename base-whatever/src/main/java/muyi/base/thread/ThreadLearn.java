package muyi.base.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author: Yang Fan
 * @date: 2019-09-02
 * @desc: 线程知识学习
 * @see Thread
 */
public class ThreadLearn {

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
     * 这里介绍2种方式：interrupt和通过标识位
     */
    public static class SafeShutdown {

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

    /**
     * 线程间通信
     * Java支持多个线程同时访问一个对象或者对象的成员变量，由于每个线程可以拥有这个变量的拷贝
     * （虽然对象以及成员变量分配的内存是在共享内存中，但每个执行的线程还是可以拥有一份拷贝，目的是加速程序的执行
     * volatile关键词的作用可以简单的理解成 关掉拷贝, 但会降低程序执行的效率
     * synchronized可以修饰方法或代码块，它可以确保在某个时刻，只能有一个线程处于同步块中，保证了线程对变量访问的可见性和排他性
     */

    /**
     * 等待/通知机制
     * <p>
     * notify 通知在对象上等待的线程，使其从wait方法返回，当然前提是该线程拿到了该对象的锁
     * wait   调用该方法的线程进入WAITING状态，只用等待另外线程的通知或被中断才会返回，调用wait方法后 会释放对象的锁
     */

    public static class WaitNotify {
        static boolean flag = true;

        static Object lock = new Object();

        /**
         * Thread[WaitThread,5,main] flag is true. wait@ 16:57:47
         * Thread[NotifyThread,5,main] hold lock. notify@16:57:48
         * Thread[WaitThread,5,main] flag is false. running@ 16:57:53
         * Thread[NotifyThread,5,main] hold lock again. sleep@ 16:57:53
         * <p>
         * 几个关键点：
         * 1. notify和wait使用前都需要对对象加锁
         * 2. wait方法返回的前提是重新获得了对象的锁（调用时释放了锁
         * 3. notifyAll将所有等待队列（WAITING）的线程移到同步队列（BLOCKED）
         */
        public static void main(String[] args) throws Exception {
            Thread waitThread = new Thread(new Wait(), "WaitThread");
            waitThread.start();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception ignore) {
            }

            Thread notifyThread = new Thread(new Notify(), "NotifyThread");
            notifyThread.start();
        }

        static class Wait implements Runnable {
            @Override
            public void run() {
                // 首先都是要加锁的
                synchronized (lock) {
                    // 当条件不满足时，继续wait 同时释放lock
                    try {
                        while (flag) {
                            System.out.println(Thread.currentThread() + " flag is true. wait@ " +
                                    new SimpleDateFormat("HH:mm:ss").format(new Date()));
                            lock.wait();
                        }
                    } catch (InterruptedException ignore) {
                    }

                    // 条件满足时，完成工作
                    System.out.println(Thread.currentThread() + " flag is false. running@ " +
                            new SimpleDateFormat("HH:mm:ss").format(new Date()));
                }
            }
        }

        static class Notify implements Runnable {
            @Override
            public void run() {
                // 首先都是要加锁的
                synchronized (lock) {
                    // 进行通知 通知时不会释放lock
                    // 知道当前线程释放了lock后，waitThread才能从wait方法返回(这一点非常重要
                    System.out.println(Thread.currentThread() + " hold lock. notify@" +
                            new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    lock.notifyAll();
                    flag = false;

                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (Exception ignore) {
                    }
                }
                // 如果在同步块外修改flag 是否可能导致waitThread先醒来 因flag还是true又重新wait 唤醒失败
                // flag = false; // 不能写在这里 也违反了可见性

                // 再次加锁
                synchronized (lock) {
                    System.out.println(Thread.currentThread() + " hold lock again. sleep@ " +
                            new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (Exception ignore) {
                    }
                }
            }
        }
    }

    /**
     * 等待/通知的基本范式
     *
     * 消费方遵循如下原则：
     * 1）获取对象的锁
     * 2）如果条件不满足 那么调用对象的wait方法，被通知后任要检查条件（写成while循环
     * 3）条件满足则执行响应的逻辑
     *
     * synchronized(lock) {
     *     while(条件不满足) {
     *         lock.wait();
     *     }
     * }
     * // 相应的处理逻辑
     *
     * 通知方遵循如下原则：
     * 1) 获取对象的锁
     * 2）改变条件
     * 3）通知所有等待在对象上的线程
     *
     * synchronized(lock) {
     *     // 改变条件
     *     lock.notifyAll();
     * }
     *
     */

    /**
     * Thread.join()的使用
     * 如果一个线程A执行了threadB.join() 含义是：当前线程A等待线程B终止后才从threadB.join()返回
     * 线程终止时，会调用线程自身的notifyAll方法，让join方法从wait中醒来，既而判断isAlive()为false 从join()方法返回 (标准的等待/通知模式）
     *
     *
     * <p>
     * public final synchronized void join() throws InterruptedException {
     * // 条件不满足 继续等待
     * while (isAlive()) {
     * wait(0);
     * }
     * // 条件满足 返回
     * }
     * <p>
     */
    public static class Join {
        public static void main(String[] args) throws Exception {
            Thread previous = Thread.currentThread();
            for (int i = 0; i < 10; i++) {
                // 每个线程拥有前一个线程的引用 需要等待前一个线程的终止
                Thread thread = new Thread(new Domino(previous), String.valueOf(i));
                thread.start();
                previous = thread;
            }
        }

        static class Domino implements Runnable {
            private Thread thread;

            public Domino(Thread thread) {
                this.thread = thread;
            }

            @Override
            public void run() {
                try {
                    thread.join();
                } catch (InterruptedException ignore) {
                }
                System.out.println(Thread.currentThread().getName() + " terminate.");
            }
        }

    }

}
