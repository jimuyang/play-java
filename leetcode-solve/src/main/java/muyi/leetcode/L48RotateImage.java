package muyi.leetcode;

import org.junit.Test;

/**
 * @author: Yang Fan
 * @date: 2019-06-15
 * @desc: Given input matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * <p>
 * rotate the input matrix in-place such that it becomes:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 */
public class L48RotateImage {

    public void rotate(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        int width = matrix[0].length;
        int height = matrix.length;

        for (int t = 0; t < height; t++) {
            for (int i = t; i < width - t - 1; i++) {
                // 旋转开始
                int temp = matrix[t][i];

                // [0, 1] <== [2, 0]
                matrix[t][i] = matrix[height - 1 - i][t];
                // [2, 0] <== [3, 2]
                matrix[height - 1 - i][t] = matrix[height - 1 - t][height - 1 - i];
                // [3, 2] <== [1, 3]
                matrix[height - 1 - t][height - 1 - i] = matrix[i][height - 1 - t];

                matrix[i][height - 1 - t] = temp;
            }
        }
    }

    @Test
    public void test() {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        this.rotate(matrix);
    }
}
