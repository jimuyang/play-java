package muyi.leetcode;

import muyi.leetcode.struct.ListNode;
import org.junit.Test;

import javax.xml.soap.Node;

/**
 * @author: Jimu Yang
 * @date: 2019/1/16 9:54 PM
 * @descricption: want more.
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * <p>
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * Example:
 * Given this linked list: 1->2->3->4->5
 * For k = 2, you should return: 2->1->4->3->5
 * For k = 3, you should return: 3->2->1->4->5
 */
public class L25ReverseNodesInKGroup {


    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null)
            return null;
        ListNode temp = head;
        ListNode before = null;

        while (temp != null) {
            NodeSegment segment = this.reverseKNodes(temp, k);
            if (segment.tail == null) {
                // 长度不足k reverse
                temp = segment.head;
                k = -1;
                continue;
            }
            if (before == null) {
                head = segment.head;
            } else {
                before.next = segment.head;
            }

            before = segment.tail;
            temp = before.next;
        }
        return head;
    }

    /**
     * reverse k nodes after head (includes head)
     *
     * @param head
     * @param k
     * @return node segment |3->2->1| ->4->5 k=3
     */
    private NodeSegment reverseKNodes(ListNode head, int k) {
        boolean reverseAll = k == -1;

        ListNode before = null; // 前一个node
        ListNode temp = null; // 当前访问node
        if (head == null)
            return null;

        // 此时在访问第2个node
        before = head;
        temp = head.next;
        head = before;
        k--;

        while ((k > 0 || reverseAll) && temp != null) {
            before.next = temp.next;
            temp.next = head;
            head = temp;
            temp = before.next;
            k--;
        }
        return new NodeSegment(head, k <= 0 || reverseAll ? before : null);
    }

    @Test
    public void testReverseKNodes() {
        ListNode head = new ListNode(1);
        ListNode temp = head;
        for (int i = 0; i < 4; i++) {
            temp.next = new ListNode(i + 2);
            temp = temp.next;
        }
        System.out.println(temp.val);

//        NodeSegment segment = this.reverseKNodes(head, 2);

        head = this.reverseKGroup(head, 3);
        System.out.println(head);
    }

    class NodeSegment {
        ListNode head;
        ListNode tail;

        NodeSegment(ListNode head, ListNode tail) {
            this.head = head;
            this.tail = tail;
        }
    }

}
