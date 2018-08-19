package muyi.leetcode;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
public class L15ThreeSum {

    /**
     * Given an array num of n integers,
     * are there elements a, b, c in nums such that a + b + c = 0?
     * Find all unique triplets in the array which gives the sum of zero.
     * <p>
     * The solution set must not contain duplicate triplets.
     */
    public List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < num.length - 2; i++) {
            if (i == 0 || (i > 0 && num[i] != num[i - 1])) {
                int lo = i + 1, hi = num.length - 1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        while (lo < hi && num[lo] == num[lo + 1]) lo++;
                        while (lo < hi && num[hi] == num[hi - 1]) hi--;
                        lo++;
                        hi--;
                    } else if (num[lo] + num[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        return res;
    }


    /**
     * 简单的挑出一个 然后在剩下的元素中找两个相加等于负数
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> myThreeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int need = 0 - nums[i];

            int lo = i + 1;
            int hi = nums.length - 1;

            while (lo < hi) {
                if (nums[lo] + nums[hi] == need) {
                    result.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                    while (lo < hi && nums[lo] == nums[lo + 1]) {
                        lo++;
                    }
                    while (lo < hi && nums[hi] == nums[hi - 1]) {
                        hi--;
                    }
                    lo++;
                    hi--;
                } else if (nums[lo] + nums[hi] < need) {
                    lo++;
                } else {
                    hi--;
                }
            }
        }
        return result;
    }


}
