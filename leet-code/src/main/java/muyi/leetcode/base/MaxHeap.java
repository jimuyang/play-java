package muyi.leetcode.base;

/**
 * @author: Jimu Yang
 * @date: 2019/3/17 3:22 PM
 * @descricption: want more.
 * @see java.util.PriorityQueue
 * 手工实现最大堆 完全二叉树
 */
public class MaxHeap<T extends Comparable<T>> {

    private transient Object[] data;

    private int capacity;

    private int size;

    public MaxHeap(int capacity) {
        this.data = new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    public void add(T value) {
        if (value == null)
            throw new NullPointerException();
        if (size >= capacity)
            throw new IndexOutOfBoundsException();
        siftUp(size++, value);
    }

    @SuppressWarnings("unchecked")
    public T poll() {
        if (size == 0)
            return null;
        int last = --size;
        T max = (T) data[0];
        T x = (T) data[last];
        data[last] = null;
        if (last != 0)
            siftDown(0, x);
        return max;
    }

    @SuppressWarnings("unchecked")
    public T max() {
        return size == 0 ? null : (T) data[0];
    }

    /**
     * 小值下沉
     */
    @SuppressWarnings("unchecked")
    private void siftDown(int index, T value) {
        int lastNonLeaf = (size >>> 1) - 1; // 这里可能-1 size为0，1
        while (index <= lastNonLeaf) {
            // 是非页节点 left
            int child = (index << 1) + 1;
            Object c = data[child];
            int right = child + 1;
            // 取left right较大值进行比较
            if (right < size && ((T) c).compareTo((T) data[right]) < 0)
                c = data[child = right];
            if (value.compareTo((T) c) >= 0) {
                // 已经是最大 不需要下沉
                break;
            }
            // 将child提升 目标index为child（已下沉
            data[index] = c;
            index = child;
        }
        data[index] = value;
    }

    /**
     * 大值上升
     */
    @SuppressWarnings("unchecked")
    private void siftUp(int index, T value) {
        while (index > 0) {
            int parent = (index - 1) >>> 1;
            Object p = data[parent];
            if (((T) p).compareTo(value) >= 0) {
                // 遇到了更大的parent stop
                break;
            }
            data[index] = p;
            index = parent;
        }
        data[index] = value;
    }

}
