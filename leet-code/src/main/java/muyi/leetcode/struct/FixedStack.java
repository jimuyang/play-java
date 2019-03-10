package muyi.leetcode.struct;

/**
 * @author: Jimu Yang
 * @date: 2019/3/10 2:08 PM
 * @descricption: want more.
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

    public void clear() {
        index = 0;
    }

    public int size() {
        return index;
    }
}
