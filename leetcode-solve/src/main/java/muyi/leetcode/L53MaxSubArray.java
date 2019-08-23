package muyi.leetcode;

/**
 * @author: Yang Fan
 * @date: 2019-08-23
 * @desc:
 */
public class L53MaxSubArray {

    /**
     * 给一个整数数组nums 找到具有最大和的连续字数组
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }

        int result = nums[0];
        int temp = 0;
        for (int i =0 ;i < nums.length ; i++) {
            int t = nums[i];
            result = t > result ? t : result;

            if (t > 0) {
                if (temp > 0) {
                    temp += t;
                    result = temp > result? temp: result;
                } else {
                    temp = t;
                }
            } else {

                temp += t;
            }
        }
        return result;
    }
}
