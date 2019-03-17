package muyi.leetcode.base;

/**
 * @author: Jimu Yang
 * @date: 2019/3/17 3:22 PM
 * @descricption: want more.
 * @see java.util.PriorityQueue
 * 手工实现最大堆 完全二叉树
 */
public class MaxHeap<T extends Comparable<T>> {

    transient Object[] data;

    int size;

    public MaxHeap(int capacity) {
        data = new Object[capacity];
        size = 0;
    }

    public void add(T value) {

        Comparable<T> comparable = (Comparable<T>) value;

//        Comparable<? super T> key = (Comparable<? super T>) value;
//        key.compareTo(value);

    }

    public T poll() {

    }

    public T max() {

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
