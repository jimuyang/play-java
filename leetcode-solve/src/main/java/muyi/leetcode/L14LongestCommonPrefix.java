package muyi.leetcode;

/**
 * 输出一些str的共同最长前缀
 *
 * @author: Jimu Yang.
 */
public class L14LongestCommonPrefix {


    /**
     * Write a function to find the longest common prefix string amongst an array of strings.
     * If there is no common prefix, return an empty string "".
     *
     * Input: ["flower","flow","flight"]
     * Output: "fl"
     */

    /**
     * 先来最普通的做法
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();

        int index = 0;
        int size = strs.length;

        int minLength = -1;
        for (int i = 0; i < size; i++) {
            int length = strs[i].length();
            minLength = minLength == -1 ? length : (length < minLength ? length : minLength);
        }

        while (index < minLength) {
            char common = '0';
            for (int i = 0; i < size; i++) {
                String str = strs[i];
                char c = str.charAt(index);

                if (common == '0') {
                    common = c;
                    continue;
                }

                if (c != common) {
                    return sb.toString();
                }
            }
            index++;
            sb.append(common);

        }

        return sb.toString();
    }

    /**
     * 两两求最长前缀
     * 水平扫描
     */
    public String HorizontalScanning(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            prefix = this.longestCommonPrefix(prefix, strs[i]);
        }
        return prefix;
    }

    private String longestCommonPrefix(String str1, String str2) {
        String prefix = str1;
        while (str2.indexOf(prefix) != 0) {
            prefix = prefix.substring(0, prefix.length() - 1);
            if (prefix.isEmpty()) {
                return "";
            }
        }
        return prefix;
    }
}
