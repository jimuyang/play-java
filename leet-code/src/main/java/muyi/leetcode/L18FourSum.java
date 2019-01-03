package muyi.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * @author: Jimu Yang
 * @date: 2019/1/3 8:44 PM
 * @descricption: want more.
 */
public class L18FourSum {


    public List<List<Integer>> fourSum(int[] nums, int target) {
        /*
        方案：依次取2个数相加得到sum1, 此时需要从剩下的数里得到和为 target - sum1 的2元组，至此转化为TwoSum问题
        思考后发现 这里剩下的数应该是指 [j,length]的切片 这样顺便也防重
         */
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        if (length < 4) {
            return result;
        }
        // 依次取2个数 一个双游标遍历
        for (int i = 0; i < length; i++) {
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < length; j++) {
                // 去重
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int target1 = target - nums[i] - nums[j];
                List<List<Integer>> twoGroups = this.twoSum(nums, target1, j);
                if (twoGroups.size() == 0) {
                    continue;
                }
                for (int m = 0; m < twoGroups.size(); m++) {
                    List<Integer> twoGroup = twoGroups.get(m);
                    List<Integer> fourGroup = new ArrayList<>();
                    fourGroup.add(nums[i]);
                    fourGroup.add(nums[j]);
                    fourGroup.add(nums[twoGroup.get(0)]);
                    fourGroup.add(nums[twoGroup.get(1)]);
                    result.add(fourGroup);
                }
            }
        }
        return result;
    }

    /**
     * 变形的TwoSum
     *
     * @param nums   总nums
     * @param target 目标和
     * @param offset 切片起点
     * @return
     */
    public List<List<Integer>> twoSum(int[] nums, int target, int offset) {
        //单趟的twoSum解法 使用一个hashMap
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = offset + 1; i < length; i++) {
            int need = target - nums[i];
            Integer index = map.get(need);
            if (index != null && index > 0) {
                List<Integer> temp = new ArrayList<>(2);
                temp.add(index);
                temp.add(i);
                result.add(temp);
                // 防重
                map.put(need, -index);
            } else {
                map.putIfAbsent(nums[i], i);
            }
        }
        return result;
    }


    @Test
    public void test() {
        int[] nums = {0, 2, 2, 2, 10, -3, -9, 2, -10, -4, -9, -2, 2, 8, 7};
        int target = 6;
        List<List<Integer>> resultSet = this.fourSum(nums, target);
        resultSet.forEach(list -> list.forEach(System.out::println));
    }


}
