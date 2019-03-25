package muyi.leetcode;

import org.junit.Test;

/**
 * @author: Jimu Yang
 * @date: 2019/3/17 1:52 AM
 * @descricption: want more.
 * <p>
 * Given a sorted array and a target value,
 * return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * You may assume no duplicates in the array.
 */
public class L35SearchInsertPos {

    /**
     * 又是二分查找的变种
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    public int searchInsert1(int[] nums, int target) {
        if (nums.length == 0) return 0;

        int start = 0;
        int end = nums.length - 1;
        int mid;

        while (true) {
            if (start == end) {
                return nums[start] < target ? start + 1 : start;
            }
            if (start > end) {
                return start;
            }

            mid = (start + end) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > target)
                end = mid - 1;
            else
                start = mid + 1;
        }
    }

    @Test
    public void test() {
        int[] nums = new int[]{1, 3, 5};
        System.out.println(searchInsert1(nums, 0));
    }

    /**
     * 先写个二分
     */
    public static int binarySearch(int[] nums, int target, int start, int end) {
        if (start == end) {
            return nums[start] < target ? start + 1 : start;
        }
        if (start > end) {
            return start;
        }
//        if (start > end) return -1;
        int mid = (start + end) / 2;
        if (nums[mid] == target)
            return mid;
        else if (nums[mid] > target)
            return binarySearch(nums, target, start, mid - 1);
        else
            return binarySearch(nums, target, mid + 1, end);
    }
}
