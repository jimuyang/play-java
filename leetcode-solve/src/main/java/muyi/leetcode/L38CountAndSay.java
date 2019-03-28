package muyi.leetcode;

import org.junit.Test;

/**
 * @author: Yang Fan
 * @date: 2019-03-28
 * @desc: count and say
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 */
public class L38CountAndSay {

    @Test
    public void test() {
        System.out.println(this.countAndSay(5));
    }

    /**
     * 最容易想到的是递归
     */
    public String countAndSay(int n) {
        if (n == 1) return "1";

        String forRead = countAndSay(n - 1);
        // 111221
        char[] chars = forRead.toCharArray();
        int len = chars.length;

        char now = chars[0];
        int count = 1;
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < len; i++) {
            char current = chars[i];
            if (current == now) {
                count++;
                continue;
            }
            sb.append(count).append(now);
            now = current;
            count = 1;
        }
        sb.append(count).append(now);

        return sb.toString();
    }
}
