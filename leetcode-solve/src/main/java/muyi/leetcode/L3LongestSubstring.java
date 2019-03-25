package muyi.leetcode;

/**
 * @Author: muyi-corp
 * @Date: Created in 13:21 2018/1/30
 * @Description:
 */

/*
Given a string, find the length of the longest substring without repeating characters.
Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.
Given "bbbbb", the answer is "b", with the length of 1.
Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */



/*
 * string.subString()
 * 一个新的字符串，该字符串值包含 stringObject 的一个子字符串，
 * 其内容是从 start 处到 stop-1 处的所有字符，其长度为 stop 减 start。
 *
 * 包括start 不包括stop
 * string.subString(0, length) 得到本身
 * 如果参数 start 与 stop 相等，那么该方法返回的就是一个空串（即长度为 0 的字符串）。
 * 如果 start 比 stop 大，那么该方法在提取子串之前会先交换这两个参数。
 */
public class L3LongestSubstring {

    public int lengthOfLongestSubstring(String s) {

        int start = 0;
        int maxLength = 0;

        if (s == null || s.equals("")) {
            return 0;
        }
        int stringLength = s.length();
        if (stringLength == 0) return 0;

        String subs = "";
        //System.out.println("substring: " + subs);

        for (int i = 0; i < stringLength; i++) {

            char nextChar = s.charAt(i);

            int findIndex = subs.indexOf(nextChar);

            if (findIndex >= 0) {
                //字串中包括了end字符->此时从子串中找到的index下一个作为start继续寻找，并记录此时子串长度
                start = start + findIndex + 1;
            } else {
                //找不到char 可以将char加入subs中
                //subs = s.substring(start, i+1);
            }
            subs = s.substring(start, i+1);

            int subsLength = subs.length();

            if (subsLength > maxLength) {
                maxLength = subsLength;
            }
            System.out.println("substring: "+ subs);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String string = "abcdabcd";
        int i = new L3LongestSubstring().lengthOfLongestSubstring(string);
        System.out.println(i);
    }

}
