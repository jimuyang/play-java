package muyi.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: Jimu Yang
 * @date: 2019/2/8 1:22 PM
 * @descricption: want more.
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place and use only constant extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * <p>
 * 1234
 * 1243
 * 1324
 * 1342
 * 1423
 * 1432
 * <p>
 * 2134
 * 2431
 * <p>
 * 3124
 * 4123
 * 4132
 * 4213
 * 4231
 * 4312
 * 4321
 * 1234
 * <p>
 * 全排列 下一个排列：next permutation
 */
public class L31NextPermutation {

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        int len = nums.length;
        // 逆序遍历 找到第一次升序的地方
        int i = len - 1;
        while (i > 0 && nums[i] <= nums[i - 1]) {
            i--;
        }

        if (i == 0) {
            // 整个都是降序排列 翻转整个数组
            reverse(nums, 0, len - 1);
            return;
        }

        int ti = i - 1;
        // 找到第一个比它大的数
        while (i < len && nums[i] > nums[ti]) {
            i++;
        }
        // swap
        int temp = nums[i - 1];
        nums[i - 1] = nums[ti];
        nums[ti] = temp;

        // 翻转降序的部分
        if (ti != len - 1) {
            reverse(nums, ti + 1, len - 1);
        }
    }


    public static void reverse(int[] nums, int start, int end) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int len = nums.length;
        start = start >= 0 && start < len ? start : 0;
        end = end > start && end < len ? end : len - 1;

        int temp;
        while (start < end) {
            temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    @Test
    public void testReverse() {
        int[] arr = new int[]{1, 2, 3, 4};
        reverse(arr, 0, 2);
        Arrays.stream(arr).forEach(System.out::println);
    }


    @Test
    public void test() {
        int[] arr = new int[]{1, 2, 3, 4};
        this.nextPermutation(arr);
        Arrays.stream(arr).forEach(System.out::println);

    }

}
