package muyi.leetcode;

import org.junit.Test;

/**
 * @author: Jimu Yang
 * @date: 2019/1/20 11:37 PM
 * @descricption: want more.
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 * Return the quotient after dividing dividend by divisor.
 * The integer division should truncate toward zero.
 * Example 1:
 * Input: dividend = 10, divisor = 3
 * Output: 3
 */
public class L29DivideTwoIntegers {


    /**
     * dividend / divisor
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 0 || dividend == 0) {
            // 应为抛出runtimeException
            return 0;
        }

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            return -dividend;
        }

        boolean positive = dividend > 0 == divisor > 0;
        // -Integer.MIN_VALUE
        dividend = dividend > 0 ? -dividend : dividend;
        divisor = divisor > 0 ? -divisor : divisor;

        int result = 0;
        while (dividend <= divisor) {
            int left = 0;
            while (divisor << left >= dividend && divisor << left < 0) {
                left++;
            }
            left--;
            dividend -= divisor << left;
            result += 1 << left;
        }

        return positive ? result : -result;
    }

    @Test
    public void test() {
        System.out.println(-3 << 2);
        System.out.println(this.divide(7, -3));
        System.out.println(this.divide(10, 2));

        System.out.println(Math.abs(Integer.MIN_VALUE));
    }


}
