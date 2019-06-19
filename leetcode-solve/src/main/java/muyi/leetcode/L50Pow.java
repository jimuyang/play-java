package muyi.leetcode;

import org.junit.Test;

/**
 * @author: Yang Fan
 * @date: 2019-06-19
 * @desc:
 */
public class L50Pow {


    /**
     * 即计算 x 的 n 次幂函数。
     * -100.0 < x < 100.0
     * n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
     */
    public double myPow(double x, int n) {
        // 最无脑的方法实现
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        if (n == 0) {
            return 1.0;
        }

        double result = x;
        for (int i = 1; i < n; i++) {
            result *= x;
        }
        return result;
    }


    @Test
    public void test() {
        System.out.println(this.myPowFast(5, 2));
    }


    public double myPowFast(double x, int n) {
        // fixme -2147483648
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }

        // 右移
        double xi = x;
        double result = 1.0;
        while (n > 0) {
            int last = n & 1;
            if (last == 1) {
                result *= xi;
            }

            n = n >> 1;
            xi *= xi;
        }

        return result;
    }


}
