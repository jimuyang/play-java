package muyi.leetcode.base;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: Jimu Yang
 * @date: 2019/3/13 9:48 PM
 * @descricption: 二分查找java实现.
 */
public class BinarySearch {

    /**
     * 递归实现
     *
     * @param nums
     * @param target
     * @return
     */
    public static int recursiveSearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        return recursiveSearch(nums, target, 0, nums.length - 1);
    }

    @Test
    public void testRecursiveSearch() {
        Random random = new Random();

        int times = 10;
        for (; times > 0; times--) {
            int length = random.nextInt(10);
            int[] nums = new int[length];
            int start = 0;
            for (int i = 0; i < length; i++) {
                nums[i] = start;
                start += random.nextInt(5);
            }
            int target = start > 0 ? random.nextInt(start) : 0;

            int find = recursiveSearch(nums, target);
            System.out.println("nums:" + Arrays.toString(nums));
            System.out.println("target:" + target);
            System.out.println("find:" + find);
        }
    }

    private static int recursiveSearch(int[] nums, int target, int start, int end) {
        int mid = (start + end) / 2;
        if (nums[mid] == target) return mid;
        if (start >= end) return -1;

        if (target > nums[mid]) {
            return recursiveSearch(nums, target, mid + 1, end);
        } else {
            return recursiveSearch(nums, target, start, mid - 1);
        }
    }


}
