package muyi.leetcode;

import org.junit.Test;

import java.util.ArrayList;
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

}
