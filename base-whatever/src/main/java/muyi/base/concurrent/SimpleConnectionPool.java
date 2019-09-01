package muyi.base.concurrent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Yang Fan
 * @date: 2019-08-31
 * @desc: 一个简单的数据库连接池示例
 */
public class SimpleConnectionPool {

    private LinkedList<Connection> pool = new LinkedList<>();

    /**
     * 连接池容量
     */
    public SimpleConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                // 初始化连接并放入连接池
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    /**
     * 释放连接
     */
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                // 释放连接后需要通知，这样其他消费者能感知到释放了一个连接
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    /**
     * 取得连接
     */
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            // 一直等待
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                // 该时间点超时
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;

                // 这里猜测是因为wait(mills)方法中的说明：
                // A thread can also wake up without being notified, interrupted, or
                //     * timing out, a so-called <i>spurious wakeup</i>.  While this will rarely
                //     * occur in practice, applications must guard against it by testing for
                //     * the condition that should have caused the thread to be awakened, and
                //     * continuing to wait if the condition is not satisfied.  In other words,
                //     * waits should always occur in loops, like this one:
                //     * <pre>
                //     *     synchronized (obj) {
                //     *         while (&lt;condition does not hold&gt;)
                //     *             obj.wait(timeout);
                //     *         ... // Perform action appropriate to condition
                //     *     }
                //     * </pre>
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }

    /**
     * jdk动态代理？
     */
    static class ConnectionDriver {
        static class ConnectionHandler implements InvocationHandler {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("commit")) {
                    TimeUnit.MILLISECONDS.sleep(100);
                }
                return null;
            }
        }

        public static final Connection createConnection() {
            return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
                    new Class<?>[]{Connection.class}, new ConnectionHandler());
        }
    }

    /**
     * 一个demo来简单测试下线程池的性能
     */
    static class ConnectionPoolTest {
        /**
         * 最多一百个连接
         */
        static SimpleConnectionPool pool = new SimpleConnectionPool(10);

        // 保证所有ConnectionRunner能够同时开始
        static CountDownLatch start = new CountDownLatch(1);

        // main线程将会等待所有ConnectionRunner结束后才能继续执行
        static CountDownLatch end;

        public static void main(String[] args) throws Exception {
            // 线程数量
            int threadCount = 30;
            end = new CountDownLatch(threadCount);

            // 每个线程需要20次连接
            int count = 20;

            AtomicInteger got = new AtomicInteger();
            AtomicInteger notGot = new AtomicInteger();

            for (int i = 0; i < threadCount; i++) {
                Thread thread = new Thread(new ConnectionRunner(count, got, notGot), "ConnectionRunnerThread");
                thread.start();
            }
            // 发令枪响
            start.countDown();
            // 所有线程都好了
            end.await();

            System.out.println("total invoke: " + (threadCount * count));
            System.out.println("got connection times: " + got);
            System.out.println("not got connection times: " + notGot);
        }

        static class ConnectionRunner implements Runnable {
            int count;
            AtomicInteger got;
            AtomicInteger notGot;

            public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
                this.count = count;
                this.got = got;
                this.notGot = notGot;
            }

            @Override
            public void run() {
                try {
                    // 发令枪
                    start.await();
                } catch (Exception ignore) {
                }

                // 模拟一个线程执行过程中 需要count个连接
                while (count > 0) {
                    try {
                        // 从连接池中获取连接 如果1000ms内无法获取到 将会返回null
                        // 分别统计 获取到为got 未获取到为notGot
                        Connection connection = pool.fetchConnection(1000);
                        if (connection != null) {
                            try {
                                // 简单模拟使用connection 耗时需要100ms
                                connection.createStatement();
                                connection.commit();
                            } finally {
                                pool.releaseConnection(connection);
                                got.incrementAndGet();
                            }
                        } else {
                            notGot.incrementAndGet();
                        }
                    } catch (Exception ignore) {
                    } finally {
                        count--;
                    }
                }
                // 我好了
                end.countDown();
            }
        }
    }


}
