package muyi.base.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Yang Fan
 * @date: 2019-07-12
 * @desc: 就是使用Lock和Condition实现线程安全的生产者-消费者模型
 */
public class ProductQueue<T> {

    private T[] items;

    private Lock lock = new ReentrantLock();

    private final static int DEFAULT_SIZE = 10;

    private Condition notFull = lock.newCondition();

    private Condition notEmpty = lock.newCondition();

    private int head;

    private int tail;

    private int count;

    public ProductQueue(int size) {
        items = (T[]) new Object[size];
    }

    public ProductQueue() {
        this(DEFAULT_SIZE);
    }

    /**
     * 生产
     */
    public void put(T t) throws InterruptedException {
        // 获取独占锁
        lock.lock();
        try {
            Thread.sleep(2 * 1000);
            while (count == getCapacity()) {
                // 队列已满 挂起当前线程 等待notFull信号
                notFull.await(); // 释放独占锁 让其他线程获取 一旦条件满足 再次获取锁
            }
            items[tail] = t;
            tail++;
            if (tail == getCapacity()) {
                tail = 0;
            }
            count++;
            // 此时队列不为空 发送notEmpty信号 唤醒消费线程
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 消费
     */
    public T get() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                // 队列已空，挂起空队列锁条件 线程，直到收到不为空的信号，被唤醒
                notEmpty.await();
            }
            T result = items[head];
            items[head] = null;
            head++;
            if (head == getCapacity()) {
                head = 0;
            }
            count--;
            // 队列已不为满，可以唤醒生产者 生产产品
            notFull.signalAll();
            return result;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 当前产品数量
     */
    public int size() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }


    /**
     * 产品队列容量
     */
    public int getCapacity() {
        return items.length;
    }

}
