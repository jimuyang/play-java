package muyi.leetcode;

/**
 * @author: Jimu Yang
 * @date: 2019/1/5 12:00 PM
 * @descricption: want more.
 */
public class L19NthNodeFromEnd {
    /**
     * Given a linked list, remove the n-th node from the end of list and return its head.
     */

    /**
     * Example:
     * Given linked list: 1->2->3->4->5, and n = 2.
     * After removing the second node from the end, the linked list becomes 1->2->3->5.
     * <p>
     * Could you do this in one pass?
     */

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 考虑n大于等于list长度时 此时head节点被删除
        ListNode lineHead = head, lineTail = head;
        for (int i = 0; i < n; i++) {
            lineHead = lineHead.next;
        }
        if (lineHead == null) {
            return head.next;
        }

        while (lineHead.next != null) {
            lineHead = lineHead.next;
            lineTail = lineTail.next;
        }

        lineTail.next = lineTail.next.next;
        return head;
    }


}
