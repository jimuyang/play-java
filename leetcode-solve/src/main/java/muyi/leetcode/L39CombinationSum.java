package muyi.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Yang Fan
 * @date: 2019-03-29
 * @desc:
 */
public class L39CombinationSum {


    @Test
    public void test() {

        int[] candidates = new int[]{2, 3, 5};
        int target = 8;
        List list = combinationSum(candidates, target);
        return;
    }

    /**
     * 效率很低的递归
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> lists = combinationSum(candidates, target, 0);
        return lists == null ? new ArrayList<>() : lists;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target, int index) {
        if (target < 0 || index == candidates.length) {
            return null;
        }
        if (target == 0) {
            List<List<Integer>> lists = new ArrayList<>();
            lists.add(new ArrayList<>());
            return lists;
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = index; i < candidates.length; i++) {
            int temp = candidates[i];
            List<List<Integer>> left = combinationSum(candidates, target - temp, i);
            if (left == null) {
                continue;
            }
            left.forEach(list -> list.add(temp));
            result.addAll(left);
        }
        return result.size() == 0 ? null : result;
    }

    /**
     * 效率高一些的递归
     */
    public List<List<Integer>> betterCombinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum(candidates, result, temp, target, 0);
        return result;
    }

    public void combinationSum(int[] candidates, List<List<Integer>> all, List<Integer> temp,
                               int target, int index) {
        if (target == 0) {
            all.add(temp);
            return;
        }

        for (int i = index; i < candidates.length && candidates[i] <= target; i++) {
            int t = candidates[i];
            List<Integer> list = new ArrayList<>(temp);
            list.add(candidates[i]);
            combinationSum(candidates, all, list, target - t, i);
        }
    }


//    /**
//     * 动态规划
//     */
//    public List<List<Integer>> dpCombinationSum(int[] candidates, int target) {
////        Arrays.sort(candidates);
//        if (candidates.length == 0) {
//            return new ArrayList<>();
//        }
//
//        List<List<Integer>>[] dp = new List[target + 1];
//
//        for (int i = 1; i <= target; i++) {
//            // 对一个target i
//            for (int c : candidates) {
//                if (dp[i] == null) {
//                    dp[i] = new ArrayList<>();
//                }
//
//                if (i > c) {
//                    if (dp[i - c] != null && dp[i - c].size() != 0) {
//                        dp[i].addAll(dp[i - c].stream().map(list -> {
//                            List<Integer> newList = new ArrayList<>(list.size() + 1);
//                            newList.addAll(list);
//                            newList.add(c);
//                            return newList;
//                        }).collect(Collectors.toList()));
//                    }
//                } else if (i == c) {
//                    dp[i].add(Collections.singletonList(c));
//                }
//            }
//        }
//        return dp[target];
//    }


}
