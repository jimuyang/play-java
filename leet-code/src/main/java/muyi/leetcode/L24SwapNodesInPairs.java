package muyi.leetcode;

import muyi.leetcode.struct.ListNode;

import java.util.List;

/**
 * @author: Jimu Yang
 * @date: 2019/1/16 9:30 PM
 * @descricption: want more.
 * Given a linked list, swap every two adjacent nodes and return its head.
 * Example:
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * <p>
 * before -> left -> right -> after  => before -> right -> left -> after
 * |                                                        |
 * temp                                                    temp
 */
public class L24SwapNodesInPairs {

    /**
     * @param head
     * @return
     * @result Runtime: 3 ms, faster than 98.99% of Java online submissions for Swap Nodes in Pairs.
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        // 交换前两个 并保存head
        ListNode second = head.next;
        head.next = second.next;
        second.next = head;
        head = second;  // new head

        ListNode temp = head.next;
        while (temp != null) {
            temp = this.swapTwoNodesAfter(temp);
        }
        return head;
    }

    private ListNode swapTwoNodesAfter(ListNode temp) {
        if (temp.next == null)
            return null;
        ListNode left = temp.next;
        if (left.next == null)
            return null;
        ListNode right = left.next;

        // swap left and right
        temp.next = right;
        left.next = right.next;
        right.next = left;

        return left;
    }


}
