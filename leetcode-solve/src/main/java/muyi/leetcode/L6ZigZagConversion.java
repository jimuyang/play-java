package muyi.leetcode;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: muyi-macpro
 * @Time: 2018/5/12 下午3:38
 * @Description:
 */
public class L6ZigZagConversion {

    /**
     * zigzag pattern
     * 蜿蜒 之字形 既锯齿又相当规则
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     */

    public String convert2ZigZag(String s, int numRows) {
        //参考 old hashMap 的实现 数组加链表
//        List<List<Character>> array = new ArrayList<>(numRows);
//        for (int i = 0; i < numRows ; i++) {
//            List<Character> linkedList = new LinkedList<>();
//            array.add(linkedList);
//        }

        char[] chars = s.toCharArray();
        int allCharNum = chars.length;

        //用二维数组好像就可以

        int planeLength = (int) Math.ceil((double) allCharNum / (numRows * 2 - 2)) * 2;
        if (numRows <= 1) {
            planeLength = allCharNum;
        }
        char[][] charPlane = new char[numRows][planeLength];

        //第一次遍历将字母填入
        int index = 0;
        for (int i = 0; i < planeLength; i++) {
            if (index >= allCharNum) {
                break;
            }

            //对于每一列 奇数列向下 偶数列向上并去头尾
            //如果在2行以下 全部向下
            //注意 i从0开始
            if (i % 2 != 0 && numRows > 2) {
                for (int j = numRows - 2; j > 0; j--) {
                    if (index >= allCharNum) {
                        break;
                    }
                    charPlane[j][i] = chars[index];
                    index++;
                }
            } else {
                for (int j = 0; j < numRows; j++) {
                    if (index >= allCharNum) {
                        break;
                    }
                    charPlane[j][i] = chars[index];
                    index++;
                }
            }

        }

        //第二次遍历 换方向将char读出
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < planeLength; j++) {
                char c = charPlane[i][j];
                if (c != 0) {
                    sb.append(charPlane[i][j]);
                }
            }
        }
        return sb.toString();
    }


}
