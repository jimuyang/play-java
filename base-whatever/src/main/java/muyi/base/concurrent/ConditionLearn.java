package muyi.base.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Yang Fan
 * @date: 2019/10/27
 * @desc:
 */
public class ConditionLearn {


    public static class conditionUseCase {
        Lock lock = new ReentrantLock();
        /**
         * 一般会讲condition对象作为成员变量
         */
        Condition condition = lock.newCondition();

        public void conditionAwait() throws InterruptedException {
            // 前置条件是：调用lock.lock()获取锁，使用lock.newCondition()获取condition
            lock.lock();
            try {
                // 调用await方法后 当前现成会释放锁 并再次等待
                condition.await();
            } catch (Exception e) {
                lock.unlock();
            }
        }

        public void conditionSignal() throws InterruptedException {
            lock.lock();
            try {
                // 通知在此condition上等待的线程
                condition.signal();
            } catch (Exception e) {
                lock.unlock();
            }
        }
    }

    public static class BoundedQueue<T> {

        private Object[] items;

        private int capacity;

        // 添加下标 移除下标 当前数量
        private int addIndex, removeIndex, count;

        private Lock lock = new ReentrantLock();
        private Condition notFull = lock.newCondition();
        private Condition notEmpty = lock.newCondition();

        public BoundedQueue(int cap) {
            this.capacity = cap;
            this.items = new Object[cap];
        }

        /**
         * 添加一个元素 如果队列满了，则添加线程进入等待 直到notFull
         */
        public void add(T t) throws InterruptedException {
            lock.lock();
            try {
                while (count == capacity) {
                    notFull.await();
                }
                items[addIndex] = t;
                if (++addIndex == capacity) {
                    addIndex = 0;
                }
                ++count;
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }

        /**
         * 取出一个元素 如果数组空了 则进入等待直到notEmpty
         */
        public T remove() throws InterruptedException {
            lock.lock();
            try {
                while (count == 0) {
                    notEmpty.await();
                }
                Object t = items[removeIndex];
                if (++removeIndex == capacity) {
                    removeIndex = 0;
                }
                count--;
                notFull.signal();
                return (T) t;
            } finally {
                lock.unlock();
            }
        }

    }

}
