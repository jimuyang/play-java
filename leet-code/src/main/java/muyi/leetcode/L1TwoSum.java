package muyi.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: muyi-corp
 * @Date: Created in 14:22 2018/1/26
 * @Description:
 */
public class L1TwoSum {

    public int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2];
        int need;
        for(int i=0; i<nums.length; i ++) {
            need = target - nums[i];

            for (int j=0; j<nums.length; j ++) {
                if (j != i && nums[j] == need){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * one-pass 使用一个map
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

}
