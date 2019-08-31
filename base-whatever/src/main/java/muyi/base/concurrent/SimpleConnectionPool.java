package muyi.base.concurrent;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author: Yang Fan
 * @date: 2019-08-31
 * @desc: 一个简单的数据库连接池示例
 */
public class SimpleConnectionPool {

    private LinkedList<Connection> pool = new LinkedList<>();

    public SimpleConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
//                pool.addLast(ConnectionDriver.creat);
            }
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                // 释放连接后需要通知，这样其他消费者能感知到释放了一个连接
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;

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

}
