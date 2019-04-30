package muyi.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Yang Fan
 * @date: 2019-04-30
 * @desc: Given a collection of distinct integers, return all possible permutations.
 */
public class L46Permutations {

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0)
            return new ArrayList<>();
        int len = nums.length;
        int size = factorial(len);

        List<List<Integer>> result = new ArrayList<>(size);
//        int[] copy = Arrays.copyOf(nums, len);

        L31NextPermutation nextPermutation = new L31NextPermutation();
        for (int i = 0; i < size; i++) {
            nextPermutation.nextPermutation(nums);
            List<Integer> t = new ArrayList<>(len);
            for (int k : nums) {
                t.add(k);
            }
            result.add(t);
        }
        return result;
    }


    public static int factorial(int n) {
        int t = 1;
        for (int i = n; i > 1; i--) {
            t *= i;
        }
        return t;
    }

    public static void swap(int i1, int i2, int[] nums) {
        if (i1 == i2) return;
        int t = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = t;
    }
}
