package muyi.leetcode;

import java.util.List;

/**
 * @author: Jimu Yang
 * @date: 2019/1/8 11:40 PM
 * @descricption: want more.
 */
public class L22GenerateParentheses {

    /**
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     * For example, given n = 3, a solution set is:
     *
     * [
     *   "((()))",
     *   "(()())",
     *   "(())()",
     *   "()(())",
     *   "()()()"
     * ]
     */
    public List<String> generateParenthesis(int n) {
        /**
         * 递进的角度思考
         * 1对括号: ()
         * 2对括号: ()(), (())
         * 3对括号: ()()(), (()()), ()(()), (())(), ((()))
         * 能类推吗 去重的问题怎么解决
         */


    }
}
