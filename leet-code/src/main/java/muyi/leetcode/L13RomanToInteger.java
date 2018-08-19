package muyi.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
public class L13RomanToInteger {

    /**
     * Symbol       Value
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     */

    private static final char[][] ROMAN_CHAR = {{'I', 'V'}, {'X', 'L'}, {'C', 'D'}, {'M', '0'}};

    public static final Map<Character, Integer> ROMAN_MAP;

    static {
        ROMAN_MAP = new HashMap<>();
        ROMAN_MAP.put('I', 1);
        ROMAN_MAP.put('V', 5);
        ROMAN_MAP.put('X', 10);
        ROMAN_MAP.put('L', 50);
        ROMAN_MAP.put('C', 100);
        ROMAN_MAP.put('D', 500);
        ROMAN_MAP.put('M', 1000);
    }

    public int romanToInt(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
//        int[] ints = new int[length];

        int temp = 0;
        int result = 0;
        for (int i = 0; i < length; i++) {
            int value = ROMAN_MAP.get(chars[i]);
            if (temp == 0) {
                temp = value;
                continue;
            }
            if (temp >= value) {
                result += temp;

            } else {
                result -= temp;

            }
            temp = value;

        }
        result += temp;
        return result;
    }

}
