package muyi.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * <p>
 * Example:
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 * @author: Jimu Yang.
 */
public class L16ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        // 将数字从小到大排序
        Arrays.sort(nums);
        Integer closest = null;
        Integer distance = null;

        for (int i = 0; i < nums.length - 2; i++) {

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int lo = i + 1;
            int hi = nums.length - 1;

            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (sum == target) {
                    return target;
                } else if (sum < target) {
                    lo++;
                } else {
                    hi--;
                }
                int temp = Math.abs(sum - target);

                if (closest == null || temp < distance) {
                    closest = sum;
                    distance = temp;
                }
            }
        }
        return closest;
    }

    @Test
    public void test() {

    }


}
