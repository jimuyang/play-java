package muyi.leetcode;

import muyi.leetcode.struct.FixedStack;
import org.junit.Test;

import java.util.Stack;

/**
 * @author: Yang Fan
 * @date: 2019-04-29
 * @desc: Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 */

public class L45JumpGameII {


    @Test
    public void test() {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(this.jump1(nums));
    }


    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int start = 0, target = len - 1;
        int count = 1;
        while ((start = furthestJump(target, nums)) > 0) {
            count++;
            target = start;
        }
        return count;
    }

    public int furthestJump(int target, int[] nums) {
        for (int i = 0; i < target; i++) {
            if (i + nums[i] >= target) {
                return i;
            }
        }
        throw new RuntimeException("cannot jump to ");
    }


    /**
     * 贪婪算法
     * 一步最远跳到2，2步最远跳到4...
     */
    public int jump2(int[] A) {
        int jumps = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < A.length - 1; i++) {
            curFarthest = Math.max(curFarthest, i + A[i]);
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }
        }
        return jumps;
    }


    /**
     * 用栈来记录jump路径
     */
    public int jump1(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;
        int len = nums.length;

//        FixedStack<Integer> stack = new FixedStack<>(len);
        Stack<Integer> stack = new Stack<>();
        stack.push(len - 1);

        Out:
        for (int i = len - 2; i >= 0; i--) {
            int reachable = -1;
            while (stack.peek() <= i + nums[i]) {
                // 栈顶可达
                if (stack.size() == 1) {
                    stack.push(i);
                    continue Out;
                }
                reachable = stack.pop();
            }
            stack.push(reachable);
            stack.push(i);
        }
        return stack.size() - 1;
    }


}
