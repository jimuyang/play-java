package muyi.leetcode;

/**
 * @author: Yang Fan
 * @date: 2019-04-04
 * @desc:
 */
public class L41FirstMissingPositive {


    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int len = nums.length;

        int target = 0;
        int i = -1;
        int temp;

        while(i < len ) {

            if (target > 0 && target <= len && nums[target-1] != target) {
                temp = nums[target-1];
                nums[target-1] = target;
                target = temp;
                continue;
            } else {
                i++;
            }

            if (i < len) {
                target = nums[i];
                nums[i] = 0;
            }

        }

        for(i = 0; i < len; i++) {
            if (nums[i] == 0) {
                return i+1;
            }
        }
        return len + 1;
    }



}
