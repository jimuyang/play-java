package muyi.leetcode.struct;


/**
 * @author: Jimu Yang
 * @date: 2019/3/10 2:08 PM
 * @descricption: want more.
 * 固定容量的栈实现 没有扩容 没有index溢出检查
 */
public class FixedStack<T> {

    Object[] values;
    int index; //空栈顶

    public FixedStack(int size) {
        values = new Object[size];
        index = 0;
    }

    public boolean isEmpty() {
        return index == 0;
    }

    public void push(T ch) {
        values[index] = ch;
        index++;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        index--;
        return (T) values[index];
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        return (T) values[index - 1];
    }

    public void clear() {
        index = 0;
    }

    public int size() {
        return index;
    }
}
