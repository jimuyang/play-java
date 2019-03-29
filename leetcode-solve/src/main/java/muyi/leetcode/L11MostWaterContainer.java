package muyi.leetcode;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
public class L11MostWaterContainer {

    /**
     * 给出数轴上连续排列的柱子高度，选择两根使得"装水"最多
     * height数组长度至少为2
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int length = height.length;

        // 考虑从左柱和右柱两个维度进行排序
        int[][] leftChoice = new int[length][2];
        int leftLength = 0;
        int[][] rightChoice = new int[length][2];
        int rightLength = 0;


        // 从左向右遍历 更高的 都是左柱的备选;
        // 从右向左遍历 更高的 都是右柱的备选；
        int temp = 0;
        for (int i = 0; i < length; i++) {
            int judged = height[i];
            if (judged > temp) {
                temp = judged;
                leftChoice[leftLength][0] = i;
                leftChoice[leftLength][1] = temp;
                leftLength++;
            }
        }

        temp = 0;
        for (int i = length-1; i >= 0; i--) {
            int judged = height[i];
            if (judged > temp) {
                temp = judged;
                rightChoice[rightLength][0] = i;
                rightChoice[rightLength][1] = temp;
                rightLength++;
            }
        }

        // 对左右choice进行笛卡尔积
        temp = 0;
//        int[][] area = new int[leftLength][rightLength];
        for (int i = 0; i < leftLength; i++) {
            for (int j = 0; j < rightLength; j++) {
                int judged = this.calculateArea(leftChoice[i][0], leftChoice[i][1], rightChoice[j][0], rightChoice[j][1]);
//                area[i][j] = judged;
                if (judged > temp) {
                    temp = judged;
                }
            }
        }
        return temp;
    }

    int calculateArea(int leftIndex, int leftHeight, int rightIndex, int rightHeight) {
        int length = rightIndex - leftIndex;
        int height = leftHeight >=  rightHeight ? rightHeight : leftHeight;
        return length * height;
    }
}
