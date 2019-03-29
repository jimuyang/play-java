package muyi.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * @author: Jimu Yang
 * @date: 2019/1/8 11:40 PM
 * @descricption: want more.
 */
public class L22GenerateParentheses {

    @Test
    public void test() {
        List<String> list = this.generateParenthesis(4);
        System.out.println(list);
    }

    /**
     * Given n pairs of parentheses, write a function to generate all combinations
     * of well-formed parentheses. For example, given n = 3, a solution set is:
     * <p>
     * [ "((()))", "(()())", "(())()", "()(())", "()()()" ]
     */
    public List<String> generateParenthesis(int n) {
        /**
         * 递进的角度思考 1对括号：() 2对括号：()(), (()) 3对括号：()()(), (()()), ()(()), (())(), ((()))
         * 能类推吗 去重的问题怎么解决
         *
         * 我有了绝佳的想法 array + linkedList 小明: (((((( 中插入右括号 插入右括号的条件：左边的(大于)的数量
         */

        /**
         * 定义 ( 0 ( 1 ( 2 ( 3 其中 0123代表可能的)位置 pos[i] 代表第i个)的位置
         */
        List<String> result = new ArrayList<>();
        int[] pos = new int[n];
        // 初始化)的位置 最大位置为：n-1
        for (int i = 0; i < n; i++) {
            pos[i] = i;
        }
        // this.showArray(pos);
        result.add(this.buildString(pos));
        // 开始调整)的位置
        // 当前在调整第几个)的位置
        int current = n - 1;
        while (true) {
            if (pos[current] == n - 1) {
                // 到达最大
                if (current == 0) {
                    break;
                }
                current--;
                continue;
            }
            pos[current]++;
            // 位置调整后 需要复位后序
            for (int i = current + 1; i < n; i++) {
                pos[i] = pos[current] > i ? pos[current] : i;
            }
            // 复位后输出这一种排列 从新从最后开始调整
            current = n - 1;
            // this.showArray(pos);
            result.add(this.buildString(pos));
        }
        return result;
    }

    private String buildString(int[] pos) {
        int n = pos.length;
        int i = 0; // 第几个(,也代表位置
        int index = 0; // pos数组索引
        StringBuilder sb = new StringBuilder("(");
        while (i < n && index < n) {
            if (i == pos[index]) {
                // 此时需要画)
                sb.append(")");
                index++;
                continue;
            } else if (i < pos[index]) {
                // 此时需要画(
                sb.append("(");
                i++;
            }
        }
        return sb.toString();
    }

    // private void showArray(int[] arr) {
    // StringBuilder sb = new StringBuilder("[");
    // for (int i = 0; i < arr.length; i++) {
    // sb.append(arr[i]).append(",");
    // }
    // sb.append("]");
    // System.out.println(sb.toString());
    // }

    @Test
    public void testBuildString() {
        int[] pos = { 0, 2, 2, 3, 4 };
        System.out.println(this.buildString(pos));
    }
}
