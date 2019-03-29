package muyi.leetcode;

import muyi.leetcode.struct.ListNode;

import java.util.Arrays;

/**
 * @author: Jimu Yang
 * @date: 2019/1/12 5:46 PM
 * @descricption: merge k sorted lists.
 * <p>
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * Example:
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class L23MergeSortedLists {

    private L21Merge2SortedLists merge2SortedLists = new L21Merge2SortedLists();

    /**
     * 需要解决的问题就是归并问题
     * 2个List的合并已经在L21中解决
     * 递归解决
     */
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) {
            return null;
        }
        if (len == 1) {
            return lists[0];
        }
        if (len == 2) {
            return merge(lists[0], lists[1]);
        }

        int middle = (int) Math.ceil(len / 2.0);
        ListNode[] left = Arrays.copyOfRange(lists, 0, middle);
        ListNode[] right = Arrays.copyOfRange(lists, middle, len);
        return merge(mergeKLists(left), mergeKLists(right));
    }


    public ListNode merge(ListNode left, ListNode right) {
        return merge2SortedLists.mergeTwoLists(left, right);
    }


}
