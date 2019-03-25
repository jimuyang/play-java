package muyi.leetcode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
public class L12IntegerToRoman {


    /**
     * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     *
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     *
     * 特殊的规则只适用于以下六种情况：
     *
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     */

    /**
     * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
     */
    private static final char[][] romanChar = {{'I', 'V'}, {'X', 'L'}, {'C', 'D'}, {'M', '0'}};

    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();

        //thinking: 不考虑5，50，500时罗马数字就是十进制
        int wei = 0;//个位

        while (num > 0) {
            int digit = num % 10;
            num /= 10;
            result.insert(0, buildOneDegit(digit, wei++));
        }
        return result.toString();
    }

    String buildOneDegit(int num, int wei) {
        char one = romanChar[wei][0];
        char five = romanChar[wei][1];

        StringBuilder sb = new StringBuilder();
        if (num < 4) {
            for (int i = 0; i < num; i++) {
                sb.append(one);
            }
        } else if (num == 4) {
            sb.append(one);
            sb.append(five);
        } else if (num == 9) {
            sb.append(one);
            sb.append(romanChar[wei + 1][0]);
        } else {
            sb.append(five);
            for (int i = 0; i < num - 5; i++) {
                sb.append(one);
            }
        }
        return sb.toString();
    }


}
