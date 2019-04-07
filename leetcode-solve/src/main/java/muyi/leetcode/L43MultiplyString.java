package muyi.leetcode;

import org.junit.Test;

/**
 * @author: Yang Fan
 * @date: 2019-04-07
 * @desc: Given two non-negative integers num1 and num2 represented as strings,
 * return the product of num1 and num2, also represented as a string.
 */
public class L43MultiplyString {

    @Test
    public void test() {
        System.out.println(this.multiply1("123", "456"));
    }


    /**
     * The length of both num1 and num2 is < 110.
     * Both num1 and num2 contain only digits 0-9.
     * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
     * You must not use any built-in BigInteger library or convert the inputs to integer directly.
     */

    public String multiply(String num1, String num2) {
//        char[] chars1 = num1.toCharArray();
//        char[] chars2 = num2.toCharArray();

        int[][] ints = new int[num2.length()][num1.length() + 1];
        int[] result = new int[num1.length() + num2.length()];

        for (int i = num2.length() - 1; i >= 0; i--) {
            int carry = 0;
            for (int j = num1.length() - 1; j >= 0; j--) {
                int t = (num1.charAt(j) - '0') * (num2.charAt(i) - '0') + carry;

                ints[i][j + 1] = t % 10;
                carry = t / 10;
            }
            ints[i][0] = carry;
        }

        for (int i = num2.length() - 1; i >= 0; i--) {
            int carry = 0;
            for (int j = num1.length(); j >= 0; j--) {
                int t = result[i + j] + ints[i][j] + carry;
                result[i + j] = t % 10;
                carry = t / 10;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        boolean head0 = true;
        for (int i : result) {
            if (i != 0) {
                head0 = false;
            }
            if (!head0) {
                stringBuilder.append(i);
            }
        }
        return stringBuilder.length() == 0 ? "0" : stringBuilder.toString();
    }


    public String multiply1(String num1, String num2) {

        int[] result = new int[num1.length() + num2.length()];
        for (int i = num2.length() - 1; i >= 0; i--) {
            for (int j = num1.length() - 1; j >= 0; j--) {
                int t = (num1.charAt(j) - '0') * (num2.charAt(i) - '0') + result[i + j + 1];
                result[i + j + 1] = t % 10;
                // 这里很精妙 result中间过程中是会出现大于10的情况 单最终不会
                result[i + j] += t / 10;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i : result)
            if (stringBuilder.length() != 0 || i != 0)
                stringBuilder.append(i);
        return stringBuilder.length() == 0 ? "0" : stringBuilder.toString();

    }

}
