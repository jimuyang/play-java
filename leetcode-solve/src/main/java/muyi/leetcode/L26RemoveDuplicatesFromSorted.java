package muyi.leetcode;

import org.junit.Test;

/**
 * @author: Jimu Yang
 * @date: 2019/1/18 11:35 PM
 * @descricption: want more.
 * <p>
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * Example 1:
 * Given nums = [1,1,2],
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the returned length.
 */
public class L26RemoveDuplicatesFromSorted {


    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        int temp = nums[0];
        int index = 1;
        for (int i = 1; i < length; i++) {
            if (temp != nums[i]) {
                nums[index++] = nums[i];
                temp = nums[i];
            }
        }
        return index;
    }

    @Test
    public void test() {
        int[] arr = new int[]{1, 2, 2, 3, 3};
        int len = this.removeDuplicates(arr);
        return;
    }

}
