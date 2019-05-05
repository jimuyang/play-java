package muyi.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Yang Fan
 * @date: 2019-05-05
 * @desc: Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 */
@SuppressWarnings("Duplicates")
public class L47PermutationsII {

    @Test
    public void test() {
        int[] input = {0, 1, 0, 0, 9};

        List<List<Integer>> result = permuteUnique(input);
        result.forEach(list -> {
            list.forEach(System.out::print);
            System.out.println();
        });

//        L31NextPermutation nextPermutation = new L31NextPermutation();
//        for (int i = 0; i < 20; i++) {
//            nextPermutation.nextPermutation(input);
//            System.out.println(Arrays.toString(input));
//        }
    }


    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;

        List<List<Integer>> result = new ArrayList<>();
        if (len < 2) {
            List<Integer> t = new ArrayList<>();
            for (int k : nums) {
                t.add(k);
            }
            result.add(t);
            return result;
        }

        // 不停的做next permutation 终止条件很重要
        // 当然直接逆序排一下 下一次逆序时即完成
        Arrays.sort(nums);
        reverse(nums, 0, len - 1);
        boolean first = true;
        while (true) {
            // 逆序遍历 找到第一次升序的地方
            int i = len - 1;
            while (i > 0 && nums[i] <= nums[i - 1]) {
                i--;
            }

            if (i == 0) {
                // 整个都是降序排列
                reverse(nums, 0, len - 1);
                if (!first) {
                    List<Integer> t = new ArrayList<>(len);
                    for (int k : nums) {
                        t.add(k);
                    }
                    result.add(t);
                    break;
                } else {
                    first = false;
                    continue;
                }
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

            List<Integer> t = new ArrayList<>(len);
            for (int k : nums) {
                t.add(k);
            }
            result.add(t);
        }
        return result;
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
}
