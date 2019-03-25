package muyi.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
public class L17PhoneLetters {

    public static final Map<Integer, String> PHONE_NUMBER_MAP = new HashMap<>();

    static {
        PHONE_NUMBER_MAP.put(2, "abc");
        PHONE_NUMBER_MAP.put(3, "def");
        PHONE_NUMBER_MAP.put(4, "ghi");
        PHONE_NUMBER_MAP.put(5, "jkl");
        PHONE_NUMBER_MAP.put(6, "mno");
        PHONE_NUMBER_MAP.put(7, "pqrs");
        PHONE_NUMBER_MAP.put(8, "tuv");
        PHONE_NUMBER_MAP.put(9, "wxyz");
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        char[] chars = digits.toCharArray();
        for (char c : chars) {
            List<String> tempStringList = new ArrayList<>();
            int num = c - '0';
            String mapperStr = PHONE_NUMBER_MAP.get(num);

            char[] mapperChars = mapperStr.toCharArray();
            if (result.size() == 0) {
                for (char mc : mapperChars) {
                    result.add(mc + "");
                }
                continue;
            }

            for (char mc : mapperChars) {
                 tempStringList.addAll(result.stream().map(temp -> temp + mc).collect(Collectors.toList()));
            }
            result = tempStringList;
        }

        return result;
    }

    @Test
    public void test() {
        System.out.print(this.letterCombinations("234"));
    }
}
