package muyi.leetcode;

import muyi.leetcode.base.BinarySearch;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author: Jimu Yang
 * @date: 2019/3/13 9:41 PM
 * @descricption: want more.
 * <p>
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 */
public class L34FindFirstAndLastPos {

    @Test
    public void example1() {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int target = 8;
        // Output: [3,4]
        System.out.println(Arrays.toString(searchRange(nums, target)));
    }

    @Test
    public void example3() {
        int[] nums = new int[]{1};
        int target = 1;
        // Output: [3,4]
        System.out.println(Arrays.toString(searchRange(nums, target)));
    }

    @Test
    public void example2() {
        int[] nums = new int[]{2, 2};
        int target = 2;
        // Output: [-1,-1]
        System.out.println(Arrays.toString(searchRange(nums, target)));
    }

    int[] FAIL = new int[]{-1, -1};
    int start;
    int end;

    public int[] searchRange(int[] nums, int target) {
        int[] result = FAIL;
        if (nums == null || nums.length == 0) return result;

        // 先找到再说 并记录最后的区间
        int find = recursiveSearch(nums, target, 0, nums.length - 1, 0);
        if (find == -1) {
            return FAIL;
        } else {
            int findSmaller = recursiveSearch(nums, target, start, find - 1, -1);
            int findBigger = recursiveSearch(nums, target, find + 1, end, 1);
            result[0] = findSmaller != -1 ? findSmaller : find;
            result[1] = findBigger != -1 ? findBigger : find;

        }
        return result;
    }

    /**
     * @param tag 1：找到最大索引的target 0：找到就行 -1：找到最小索引的target
     * @return
     */
    private int recursiveSearch(int[] nums, int target, int start, int end, int tag) {
        if (start == end) {
            return nums[start] == target ? start : -1;
        }
        if (start > end) return -1;

        int mid = (start + end) / 2;
        if (nums[mid] == target) {
            switch (tag) {
                case 1:
                    int bigger = recursiveSearch(nums, target, mid + 1, end, tag);
                    return bigger == -1 ? mid : bigger;
                case -1:
                    int smaller = recursiveSearch(nums, target, start, mid - 1, tag);
                    return smaller == -1 ? mid : smaller;
                case 0:
                    this.start = start;
                    this.end = end;
                    return mid;
            }
        }

        if (target > nums[mid]) {
            return recursiveSearch(nums, target, mid + 1, end, tag);
        } else {
            return recursiveSearch(nums, target, start, mid - 1, tag);
        }
    }

}
