package muyi.leetcode;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: muyi-macpro
 * @Time: 2018/3/11 下午7:04
 * @Description: 找出一个字符串的最长 回环子串
 */

/*
Given a string s, find the longest palindromic substring in s.
You may assume that the maximum length of s is 1000.

Example:
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
 */
public class L5LongestPalindrome {

    public String longestPalindrome(String s) {
        //目前思路是分别以每个字符和空隙为对称轴
        if (s == null || s.equals("")) return null;
        int originLength = s.length();
        int max = 1; //至少1个字母
        String longestPalindromeString = s.substring(0, 1);

        //以每个字符为对称轴
        for (int i = 0; i < originLength; i++) {
            //往左右延伸的最大距离
            int maxS = i < (originLength - i) ? i : (originLength - 1 - i);

            if (maxS > 0) {
                for (int j = 1; j <= maxS; j++) {
                    if (s.charAt(i - j) == s.charAt(i + j)) {
                        //目前为止还是对称de
                        int length = j * 2 + 1;
                        if (length > max) {
                            max = length;
                            longestPalindromeString = s.substring(i - j, i + j + 1);
                        }
                    } else {
                        //以这个字符找不到更长的对称子串了
                        break;
                    }
                }
            }
        }

        //以每个空隙为对称轴
        //"baab" 长度为4 有3个空隙 分别在 0 1 2 字符之后
        for (int i = 0; i < originLength - 1; i++) {
            //最长延伸距离 最小为1
            int maxS = i + 1 < originLength - 1 - i ? i + 1 : originLength - 1 - i;
            for (int j = 1; j <= maxS; j++) {
                if (s.charAt(i - j + 1) == s.charAt(i + j)) {
                    //目前为止还是对称的
                    int length = j * 2;
                    if (length > max) {
                        max = length;
                        longestPalindromeString = s.substring(i-j+1,i+j+1);
                    }
                }else {
                    break;
                }
            }

        }

        return longestPalindromeString;
    }

    public boolean isPalindromeBySB(String s) {
        return new StringBuilder(s).reverse().toString().equals(s);
    }

    public static void main(String[] args) {
        String string = "babccb";
        String result = new L5LongestPalindrome().longestPalindrome(string);
        System.out.println(result);
    }
}
