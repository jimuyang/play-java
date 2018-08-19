package muyi.leetcode;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: muyi-macpro
 * @Time: 2018/5/12 下午5:02
 * @Description:
 */
public class L7ReverseInteger {

    /**
     * Given a 32-bit signed integer, reverse digits of an integer.
     * <p>
     * Input: 123  Output: 321
     * Input: 120  Output: 21
     * Input: -123 Output: -321
     * <p>
     * Assume we are dealing with an environment which could only store integers
     * within the 32-bit signed integer range: [−231,  231 − 1].
     * For the purpose of this problem, assume that your function returns 0
     * when the reversed integer overflows.
     */

    public int reverse(int x) {
        String str = Integer.toString(x);
        try {
            return this.parseReverseInt(str);
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }


    public int otherReverse(int x) {
        int result = 0;

        while (x != 0) {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            if ((newResult - tail) / 10 != result) {
                return 0;
            }
            result = newResult;
            x = x / 10;
        }
        return result;
    }


    private int parseReverseInt(String s) {
        return this.parseReverseInt(s, 10);
    }

    private int parseReverseInt(String s, int radix) {
        /**
         * 此方法大部分copy自 Integer.parseInt
         * 只是调整了顺序
         */

        if (s == null) {
            throw new NumberFormatException("null");
        }

        if (radix < Character.MIN_RADIX) {
            throw new NumberFormatException("radix " + radix +
                    " less than Character.MIN_RADIX");
        }

        if (radix > Character.MAX_RADIX) {
            throw new NumberFormatException("radix " + radix +
                    " greater than Character.MAX_RADIX");
        }

        int result = 0;
        boolean negative = false;
        int i = 0, len = s.length();
        int limit = -Integer.MAX_VALUE;
        int multmin;
        int digit;

        if (len > 0) {
            char firstChar = s.charAt(0);
            if (firstChar < '0') { // Possible leading "+" or "-"
                if (firstChar == '-') {
                    negative = true;
                    limit = Integer.MIN_VALUE;
                } else if (firstChar != '+')
                    throw new NumberFormatException("null");

                if (len == 1) // Cannot have lone "+" or "-"
                    throw new NumberFormatException("null");
                i++;
            }
            multmin = limit / radix;

            //从最后一位到第一个非符号位
            int index = len - 1;
            while (index >= i) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                digit = Character.digit(s.charAt(index--), radix);
                if (digit < 0) {

                    throw new NumberFormatException("null");
                }
                if (result < multmin) {
                    throw new NumberFormatException("null");
                }
                result *= radix;
                if (result < limit + digit) {
                    throw new NumberFormatException("null");
                }
                result -= digit;
            }
        } else {
            throw new NumberFormatException("null");
        }
        return negative ? result : -result;
    }


}
