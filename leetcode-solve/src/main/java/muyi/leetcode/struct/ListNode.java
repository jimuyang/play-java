package muyi.leetcode.struct;

/**
 * @author: Jimu Yang
 * @date: 2019/1/12 6:26 PM
 * @descricption: want more.
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        this.val = x;
    }

    @Override
    public String toString() {
        if (next != null)
            return val + "->" + next.toString();
        else
            return val + "";
    }
}
