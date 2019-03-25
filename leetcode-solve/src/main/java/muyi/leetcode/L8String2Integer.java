package muyi.leetcode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/5/24 下午10:25
 * @description: 实现string转化到integer
 */
public class L8String2Integer {

    /**
     * convert a string to integer
     *
     * @param str
     */
    public int myAtoi(String str) {

        if (str.isEmpty())
            return 0;
        str = str.trim();

        int i = 0, ans = 0, sign = 1, len = str.length();
        if (len == 0) {
            return 0;
        }
        if (str.charAt(i) == '-' || str.charAt(i) == '+')
            sign = str.charAt(i++) == '+' ? 1 : -1;
        for (; i < len; ++i) {
            int tmp = str.charAt(i) - '0';
            if (tmp < 0 || tmp > 9)
                break;
            if (ans > Integer.MAX_VALUE / 10
                    || (ans == Integer.MAX_VALUE / 10 && Integer.MAX_VALUE % 10 < tmp))
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            else
                ans = ans * 10 + tmp;
        }
        return sign * ans;

    }

    /**
     *
     */
    public String strTrim(String str) {
        if (str == null)
            return null;

        int length = str.length();
        char[] chars = str.toCharArray();
        int head = 0;
        int foot = length;

        while (head < foot && chars[head] <= ' ') {
            head++;
        }

        while (head < foot && chars[foot - 1] <= ' ') {
            foot--;
        }

        return (head > 0 || foot < length) ? str.substring(head, foot) : str;
    }


}

