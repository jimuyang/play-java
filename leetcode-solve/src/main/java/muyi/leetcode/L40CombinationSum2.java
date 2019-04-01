package muyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Yang Fan
 * @date: 2019-04-02
 * @desc:
 */
@SuppressWarnings("ALL")
public class L40CombinationSum2 {


    /**
     * 效率高一些的递归
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum(candidates, result, temp, target, -1);
        return result;
    }

    public void combinationSum(int[] candidates, List<List<Integer>> all, List<Integer> temp,
                               int target, int index) {
        if (target == 0) {
            all.add(temp);
            return;
        }

        int unique = -1;
        for (int i = index + 1; i < candidates.length && candidates[i] <= target; i++) {
            if (unique != -1 && candidates[i] == unique) {
                continue;
            }

            int t = candidates[i];
            List<Integer> list = new ArrayList<>(temp);
            list.add(candidates[i]);
            combinationSum(candidates, all, list, target - t, i);
            unique = t;
        }
    }
}
