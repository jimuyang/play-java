package muyi.leetcode;

import org.junit.Test;

/**
 * @author: Yang Fan
 * @date: 2019-04-09
 * @desc: Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 * <p>
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 */
public class L44WildcardMatching {


    /**
     * 前面有一道正则是 . * 区别是必须有前缀
     * 思路：用*进行分割
     */
    public boolean isMatch(String str, String pattern) {
        int s = 0, p = 0;
        int star = -1, ts = 0;

        while (s < str.length()) {
            if (p < pattern.length() && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))) {
                // 匹配上了 都向前一步
                s++;
                p++;
            }

            // 不匹配时：

            // 如果遇到* 记录下此时s，p的位置(多个*时 让前面的*最小匹配即可
            else if (p < pattern.length() && pattern.charAt(p) == '*') {
                star = p;
                ts = s;
                p++;
            }
            // 走到这里其实已经不匹配了 找到最后一个*
            else if (star != -1) {
                p = star + 1;
                s = ++ts;
            } else {
                return false;
            }
        }

        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;
        return p == pattern.length();
    }
//    @Test
//    public void test() {
//        System.out.println("你好hi".length());
//        char c = '你';
//    }


}
