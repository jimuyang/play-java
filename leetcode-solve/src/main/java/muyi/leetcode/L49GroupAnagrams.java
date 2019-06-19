package muyi.leetcode;

import java.util.*;

/**
 * @author: Yang Fan
 * @date: 2019-06-17
 * @desc:
 */
public class L49GroupAnagrams {

    int[] arr = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};

    /**
     * 用26个质数来代表不同字母
     */
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<Long, List<String>> resultMap = new HashMap<>(strs.length);

        for (String str : strs) {
            long key = 1;
            for (int i = 0; i < str.length(); i++) {
                key *= arr[str.charAt(i) - 'a'];
            }

            List<String> list = resultMap.computeIfAbsent(key, k -> new LinkedList<>());
            list.add(str);


//            resultMap.compute(key, (k, o) -> {
//                if (o != null) {
//                    o.add(str);
//                    return o;
//                } else {
//                    List<String> n = new ArrayList<>();
//                    n.add(str);
//                    return n;
//                }
//            });
        }

        List<List<String>> result = new ArrayList<>();
        for (List<String> list : resultMap.values()) {
            if (list != null) {
                result.add(list);
            }
        }
        return result;
    }


    /**
     * 真的是蠢了
     */
    public List<List<String>> groupAnagrams1(String[] strs) {

        Map<String, List<String>> resultMap = new HashMap<>();

        for (String str : strs) {

            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String nstr = new String(chars);

            List<String> list = resultMap.computeIfAbsent(nstr, k -> new LinkedList<>());
            list.add(str);
        }
        List<List<String>> result = new ArrayList<>();
        for (List<String> list : resultMap.values()) {
            if (list != null) {
                result.add(list);
            }
        }
        return result;

    }


}
