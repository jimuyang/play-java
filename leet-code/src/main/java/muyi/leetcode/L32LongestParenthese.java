package muyi.leetcode;

import muyi.leetcode.struct.FixedStack;
import org.junit.Test;

import java.util.Stack;

/**
 * @author: Jimu Yang
 * @date: 2019/3/8 12:08 AM
 * @descricption: want more.
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 */
public class L32LongestParenthese {

    public int longestValidParentheses2(String s) {
        if (s == null || s.length() == 0) return 0;
        int longest = 0;
        int pair, old, temp;

        FixedStack<Integer> stack = new FixedStack<>(s.length() + 1);
        stack.push(0);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(0);
            } else {
                if (stack.size() > 1) {
                    pair = stack.pop();
                    old = stack.pop();
                    temp = old + pair + 1;
                    longest = longest > temp ? longest : temp;
                    stack.push(temp);
                } else {
                    stack.clear();
                    stack.push(0);
                }
            }
        }
        return longest * 2;
    }

//    public int longestValidParentheses1(String s) {
//        if (s == null || s.length() == 0) return 0;
//        char[] chararr = s.toCharArray();
//        int[] intarr = new int[chararr.length];
//
//        int stack = 0;
//        for (int i = 0; i < chararr.length; i++) {
//            if (chararr[i] == '(') {
//                stack++;
//            } else {
//                if (stack > 0) {
//                    // 凑成了对子
//                    stack--;
//                    intarr[i] = stack + 1; // 记录此时栈深 +1因int[]初始为0
//                } else {
//                    // 破坏
//                    intarr[i] = -1; // 竖墙 -1
//                }
//            }
//        }
//
//        // 至此 转化为求最远点问题
//
//    }

    /**
     * 失败解法 ()(()
     */
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;

        int stack = 0, pairNum = 0;
        int max = 0;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack++;
            } else {
                if (stack > 0) {
                    stack--;
                    pairNum++;
                } else {
                    max = max > pairNum ? max : pairNum;
                    pairNum = 0;
                }
            }
        }
        max = max > pairNum ? max : pairNum;
        return max * 2;
    }


    @Test
    public void test() {
        System.out.println(this.longestValidParentheses2("()(()"));
        System.out.println(this.longestValidParentheses2("()()("));
    }

}
