package muyi.leetcode.base;

import org.junit.Test;

/**
 * @author: Yang Fan
 * @date: 2019-04-02
 * @desc: 动态规划
 */
public class DynamicProgramming {


    public static int fibonacci(int n) {
        int fib_i_2 = 1, fib_i_1 = 1, fib_i;

        for (int i = 3; i <= n; i++) {
            fib_i = fib_i_1 + fib_i_2;
            fib_i_2 = fib_i_1;
            fib_i_1 = fib_i;
        }
        return fib_i_1;
    }

    /**
     * use recursion
     */
    public static int recuriveFibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return recuriveFibonacci(n - 1) + recuriveFibonacci(n - 2);
    }

    @Test
    public void testFibonacci() {
        System.out.println(recuriveFibonacci(8));
        System.out.println(fibonacci(8));
        System.out.println(fibonacci(1));
        System.out.println(fibonacci(2));
    }

    /**
     * 银币找零问题
     */

    /**
     * n皇后问题
     */


}
