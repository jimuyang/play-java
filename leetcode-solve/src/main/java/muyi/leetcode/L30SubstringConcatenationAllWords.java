package muyi.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * @author: Jimu Yang
 * @date: 2019/1/26 4:31 PM
 * @descricption: want more.
 * You are given a string, s, and a list of words, words,
 * that are all of the same length.
 * Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once
 * and without any intervening characters.
 * Example 1:
 * Input:
 * s = "barfoothefoobarman",
 * words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
 * The output order does not matter, returning [9,0] is fine too.
 */
public class L30SubstringConcatenationAllWords {


    @Test
    public void testLast() {
        List<Integer> result = this.findSubStringLast("barfoothefoobarman", new String[]{"foo", "bar"});
        result.forEach(System.out::println);
    }

    public List<Integer> findSubStringLast(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        if (words == null || s == null || s.length() < 1 || words.length < 1) {
            return result;
        }

        int eachWordLen = words[0].length();
        int subStringLen = eachWordLen * words.length;

//        Arrays.sort(words);
        Map<String, Integer> wordsMap = countWords(words);
        String first = words[0];

        int i = -1;
        while (true) {
            i = s.indexOf(first, i + 1);
            if (i < 0) {
                return new ArrayList<>(new HashSet<Integer>(result));
            }

            String[] temp = new String[words.length];
            Map<String, Integer> tempMap = new HashMap<>();

            // 划定范围
            int start = i - (subStringLen - eachWordLen);
            for (int j = start; j <= i; j += eachWordLen) {
                if (j < 0 || j + subStringLen > s.length()) {
                    continue;
                }
                tempMap.clear();
                String substr = s.substring(j, j + subStringLen);
                boolean is = true;
                for (int k = 0; k < substr.length(); k++) {
                    if (k % eachWordLen == 0) {
                        String oneWord = substr.substring(k, k + eachWordLen);

                        tempMap.compute(oneWord, (key, old) -> old == null ? 1 : old + 1);
                        if (tempMap.get(oneWord) > wordsMap.getOrDefault(oneWord, 0)) {
                            is = false;
                            break;
                        }
                    }
                }
//                if (this.wordsEquals(temp, words)) {
//                    result.add(j);
//                }
//                if (this.wordCountEquals(temp, wordsMap)) {
                if (is) result.add(j);
//                }
            }
        }
    }

    private boolean wordsEquals(String[] strs, String[] words) {
        if (strs == null || strs.length != words.length) {
            return false;
        }
//        Arrays.sort(words);
        Arrays.sort(strs);

        for (int i = 0; i < strs.length; i++) {
            if (strs[i] == null || !strs[i].equals(words[i])) {
                return false;
            }
        }
        return true;
    }

    public static Map<String, Integer> countWords(String[] words) {
        Map<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            countMap.compute(word, (key, old) -> old == null ? 1 : old + 1);
        }
        return countMap;
    }

    private boolean wordCountEquals(String[] temp, Map<String, Integer> wordCountMap) {
        Map<String, Integer> tempMap = new HashMap<>();
        for (String word : temp) {
            tempMap.compute(word, (key, old) -> old == null ? 1 : old + 1);
            if (tempMap.get(word) > wordCountMap.getOrDefault(word, 0)) {
                return false;
            }
        }
        return true;
    }

    public static boolean strArrEquals(String[] strings1, String[] strings2) {
        if (strings1 == null && strings2 == null) {
            return true;
        }

        if (strings1 != null && strings2 != null) {
            if (strings1.length != strings2.length) {
                return false;
            }

            Arrays.sort(strings1);
            Arrays.sort(strings2);

            for (int i = 0; i < strings1.length; i++) {
                if (strings1[i] == null) {
                    if (strings2[i] == null) {
                        continue;
                    } else {
                        return false;
                    }
                } else {
                    if (!strings1[i].equals(strings2[i])) {
                        return false;
                    } else {
                        continue;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }


    // 我用的array.sort 改良下 用hashmap试试？

//    public List<Integer> findSubStringOther(String s, String[] words) {
//        List<Integer> result = new ArrayList<>();
//        int sLen = s.length(), wordNum = words.length;
//        if (sLen <= 0 || wordNum <= 0) {
//            return result;
//        }
//
//        // count word occurance
//        Map<String, Integer> wordCount = new HashMap<>();
//        for (String word : words) {
//            wordCount.compute(word, (key, old) -> old == null ? 1 : old + 1);
//        }
//
//        // travel all substring combinations
//        int eachWordLen = words[0].length();
//        for (int i = 0; i < eachWordLen; i++) {
//            int left = i, count = 0;
//            Map<String, Integer> tempMap = new HashMap<>();
//            for (int j = i; j <= sLen - eachWordLen; j += eachWordLen) {
//                String str = s.substring(j, eachWordLen);
//                //
//                if (wordCount.containsKey(str)) {
//                    tempMap.compute(str, (key, old) -> old == null ? 1 : old + 1);
//
//                }
//            }
//
//        }
//
//
//    }


    /**
     * 分割线
     */
    public List<Integer> findSubString2(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }
        int eachLen = words[0].length();
        int wordNum = words.length;

        // 遍历整个string 分成eachLen个桶
        int bucketSize = (int) Math.ceil((double) s.length() / eachLen);
        char[][] buckets = new char[eachLen][bucketSize];

        for (int i = 0, bucketIndex = 0, round = 0; i < s.length(); i++, bucketIndex++) {
            if (bucketIndex >= eachLen) {
                round++;
                bucketIndex -= eachLen;
            }
            buckets[bucketIndex][round] = s.charAt(i);
        }

        // 遍历所有words 也是eachLen个桶
        char[][] smallBucks = new char[eachLen][wordNum];
        for (int i = 0; i < wordNum; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                smallBucks[j][i] = word.charAt(j);
            }
        }

        // 用小桶去匹配大桶 先匹配首字母
        char[] headChars = smallBucks[0];
        Map<Character, Integer> headCharCountMap = new HashMap<>(headChars.length);
        for (int i = 0; i < headChars.length; i++) {
            headCharCountMap.merge(headChars[i], 1, (old, val) -> old + val);
        }

        // 遍历每个大桶
        for (int i = 0; i < buckets.length; i++) {
            char[] bigBucket = buckets[i];
            Map<Character, Integer> tempMap = new HashMap<>(wordNum);
            for (int j = 0; j < bigBucket.length; j++) {
                // 装出一个map
                // 出一个
                if (j >= wordNum) {
                    Integer c = tempMap.get(bigBucket[j - wordNum]);
                    if (c > 1) {
                        tempMap.put(bigBucket[j - wordNum], c - 1);
                    } else {
                        tempMap.remove(bigBucket[j - wordNum]);
                    }
                }
                // 进一个
                tempMap.merge(bigBucket[j], 1, (old, val) -> old + val);

                // 数量够了就开始
                if (j >= wordNum - 1) {
                    if (mapEquals(tempMap, headCharCountMap)) {
                        // 首字母匹配上了 按顺序看下面第二个字母

                    }
                }

            }

        }


        return null;

    }

    public static void putOrAdd(Map<Character, Integer> charCountMap, char ch) {
        charCountMap.merge(ch, 1, (a, v) -> a + v);
    }


    /**
     * 用于char统计map的比较 因为总char数是一致的 省略了很多逻辑
     *
     * @param map1
     * @param map2
     * @return
     */
    public static boolean mapEquals(Map<Character, Integer> map1, Map<Character, Integer> map2) {
        for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
//            if (entry.getValue() == null) {
//                if (map2.get(entry.getKey()) == null) {
//                    continue;
//                } else {
//                    return false;
//                }
//            }
            if (!entry.getValue().equals(map1.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }


    /**************************
     * 下面这种递归 超时了
     **************************/
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        if (words == null || words.length == 0) {
            return result;
        }

        int eachLength = words[0].length();
        String firstWord = words[0];

        // 先找第一个word的indexes
        List<Integer> firstWordIndexes = indexes(s.toCharArray(), 0, s.length(),
                firstWord.toCharArray(), 0, eachLength, 0);

        if (firstWordIndexes.size() == 0)
            return result;

        // 每一个位置都尝试蔓延 使用一个int[]进行标记
        int[] mark = initArray(words.length);
        for (int firstWordIndex : firstWordIndexes) {
            mark = initArray(mark);
            mark[0] = 1;
            spread(s, firstWordIndex, firstWordIndex + eachLength,
                    words, mark, result);
        }

        Set<Integer> set = new HashSet<>(result);
        return new ArrayList<>(set);
    }


    private boolean spread(String source, int start, int end, String[] words, int[] mark, List<Integer> result) {
        int left = 0;
        for (int i = 0; i < words.length; i++) {
            left += mark[i] == 0 ? 1 : 0;
        }
        if (left == 0) {
            result.add(start);
            return true;
        }


        // 每个没mark的word都尝试
        for (int i = 0; i < words.length; i++) {
            if (mark[i] == 1) {
                continue;
            }

            int lr = findNeighbourString(source, start, end, words[i]);
            if ((lr & (1 << 1)) > 0) {
                // 左边找到
                mark[i] = 1;
                // 继续找
                spread(source, start - words[i].length(), end, words, mark, result);
                mark[i] = 0;
            }
            if ((lr & 1) > 0) {
                // 右边找到
                mark[i] = 1;
                // 继续找
                spread(source, start, end + words[i].length(), words, mark, result);
                mark[i] = 0;
            }
        }

        return false;
    }


    /**
     * 给定source string的一段 寻找相邻的string
     *
     * @param source source
     * @param start
     * @param end
     * @param target
     * @return 0 没找到 10左边找到 01右边找到 11两边都找到
     */
    public static int findNeighbourString(String source, int start, int end, String target) {
        int res = 0;
        int totalLen = source.length();
        int len = target.length();

        if (start - len >= 0) {
            // 左边找找
            int i = indexOf(source.toCharArray(), start - len, len,
                    target.toCharArray(), 0, len, 0);
            res |= i >= 0 ? 1 << 1 : 0;
        }

        if (end + len <= totalLen) {
            // 右边找找
            int i = indexOf(source.toCharArray(), end, len,
                    target.toCharArray(), 0, len, 0);
            res |= i >= 0 ? 1 : 0;
        }
        return res;
    }

    @Test
    public void testNeighbour() {
        int i = findNeighbourString("barfoothefoobarman", 9, 12, "bar");
        System.out.println(i);
    }

    @Test
    public void test() {
        List<Integer> result = this.findSubstring("barfoothefoobarman", new String[]{"foo", "bar"});
        result.forEach(System.out::println);
    }


    /**
     * @see String#indexOf(char[], int, int, char[], int, int, int)
     */

    static int indexOf(char[] source, int sourceOffset, int sourceCount,
                       char[] target, int targetOffset, int targetCount,
                       int fromIndex) {
        if (fromIndex >= sourceCount) {
            return (targetCount == 0 ? sourceCount : -1);
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (targetCount == 0) {
            return fromIndex;
        }

        char first = target[targetOffset];
        int max = sourceOffset + (sourceCount - targetCount);

        for (int i = sourceOffset + fromIndex; i <= max; i++) {
            /* Look for first character. */
            if (source[i] != first) {
                while (++i <= max && source[i] != first) ;
            }

            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && source[j]
                        == target[k]; j++, k++)
                    ;

                if (j == end) {
                    /* Found whole string. */
                    return i - sourceOffset;
                }
            }
        }
        return -1;


    }


    static List<Integer> indexes(char[] source, int sourceOffset, int sourceCount,
                                 char[] target, int targetOffset, int targetCount,
                                 int fromIndex) {
        List<Integer> indexes = new ArrayList<>();
        // targetCount不为0
        if (fromIndex >= sourceCount) {
            return indexes;
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (targetCount == 0) {
            return indexes;
        }

        char first = target[targetOffset];
        int max = sourceOffset + (sourceCount - targetCount);

        for (int i = sourceOffset + fromIndex; i <= max; i++) {
            /* Look for first character. */
            if (source[i] != first) {
                while (++i <= max && source[i] != first) ;
            }

            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && source[j]
                        == target[k]; j++, k++)
                    ;

                if (j == end) {
                    /* Found whole string. */
//                    return i - sourceOffset;
                    indexes.add(i - sourceOffset);
                }
            }
        }
        return indexes;
    }

    int[] initArray(int len) {
        return new int[len];
    }

    int[] initArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }
        return arr;
    }

}
