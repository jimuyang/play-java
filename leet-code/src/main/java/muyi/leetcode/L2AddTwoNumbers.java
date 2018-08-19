package muyi.leetcode;

/**
 * @Author: muyi-corp
 * @Date: Created in 14:40 2018/1/26
 * @Description:
 */

/**
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class L2AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode head = null;
        ListNode current = null;
        int needAddOne = 0;

        while (l1 != null || l2 != null) {

            int n1 = l1 == null ? 0 : l1.val;
            int n2 = l2 == null ? 0 : l2.val;

            int n = n1 + n2 + needAddOne;
            if (n >= 10) {
                n = n - 10;
                needAddOne = 1;
            } else {
                needAddOne = 0;
            }

            if (current == null) {
                current = new ListNode(n);
                head = current;
            } else {
                current.next = new ListNode(n);
                current = current.next;
            }

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        if (needAddOne != 0) {
            current.next = new ListNode(needAddOne);
        }

        return head;
    }

    /**
     * Definition for singly-linked list.
     */
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
