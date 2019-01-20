package muyi.leetcode;

import org.junit.Test;

/**
 * @author: Jimu Yang
 * @date: 2019/1/20 11:16 PM
 * @descricption: want more.
 * Implement strStr().
 * Return the index of the first occurrence of needle in haystack,
 * or -1 if needle is not part of haystack.
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 */
public class L28ImplementStr {

    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }

        if (needle.length() == 0) {
            return 0;
        }

        char[] chars = haystack.toCharArray();
        int len = haystack.length();

        char[] needChars = needle.toCharArray();
        int needLen = needle.length();

        Next:
        for (int i = 0; i <= len - needLen; i++) {

            // 从当前char开始判断是否吻合needle
            for (int j = 0; j < needLen; j++) {
                if (chars[i + j] != needChars[j]) {
                    continue Next;
                }
            }
            // 当全部吻合时
            return i;
        }
        return -1;
    }

    public int useIndexOf(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    @Test
    public void test() {
        String hello = "hello";
        String needle = "ll";
        System.out.println(this.strStr(hello, needle)); // 2
    }

}
