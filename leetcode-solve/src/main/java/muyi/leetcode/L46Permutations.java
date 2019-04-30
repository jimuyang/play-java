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


        // 第一个数
        for (int i = 0; i < len; i++) {

        }
    }


    public static int factorial(int n) {
        int t = 1;
        for (int i = n; i > 1; i--) {
            t *= i;
        }
        return t;
    }

    public static void swap(int i1, int i2, int[] nums) {
        int t = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = t;
    }
}
